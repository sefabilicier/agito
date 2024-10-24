package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerRegistrationMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import intern.customer.agitoo.Repository.Abstracts.CustomerRegistrationRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Service.Rules.CommonBusinessRules.checkIfIdExist;
import static intern.customer.agitoo.Service.Rules.toDatabase.isConnected;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegistrationServiceImpl implements ICustomerRegistrationService {


    @Autowired
    private CustomerRegistrationRepository customerRegistrationRepository;

    @Autowired
    private CustomerRegistrationMapper customerRegistrationMapper;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-registration")
    public CompletableFuture<List<CustomerRegistrationDTO>> getAll () {
        isConnected ();
        List<CustomerRegistration> customerRegistrations = customerRegistrationRepository.findAll ();
        List<CustomerRegistrationDTO> customerRegistrationDTOS = customerRegistrations
                .stream ()
                .map (customerRegistration -> customerRegistrationMapper
                        .toDTO (customerRegistration, CustomerRegistrationDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerRegistrationDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-registration", key = "#result.registrationID")
    public CompletableFuture<CustomerRegistrationDTO> add (CustomerRegistrationDTO dtoModel) {
        CustomerRegistration customerRegistration = customerRegistrationMapper.toEntity (dtoModel, CustomerRegistration.class);
        CustomerRegistration savedCustomerRegistration = customerRegistrationRepository.save (customerRegistration);

        return CompletableFuture.completedFuture (customerRegistrationMapper.toDTO (savedCustomerRegistration, CustomerRegistrationDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-registration", key = "#result.registrationID")
    public CompletableFuture<CustomerRegistrationDTO> update (CustomerRegistrationDTO dtoModel) {
        CustomerRegistration customerRegistration = customerRegistrationMapper
                .toEntity (dtoModel, CustomerRegistration.class);
        CustomerRegistration updatedCustomerRegistration = customerRegistrationRepository.save (customerRegistration);

        return CompletableFuture.completedFuture (customerRegistrationMapper.toDTO (updatedCustomerRegistration, CustomerRegistrationDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-registration", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerRegistrationRepository, id);
        customerRegistrationRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-registration", key = "#id")
    public CompletableFuture<CustomerRegistrationDTO> findById (Long id) {
        checkIfIdExist (customerRegistrationRepository, id);
        CustomerRegistration customerRegistration = customerRegistrationRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer registration not found with id: " + id));
        return CompletableFuture.completedFuture (customerRegistrationMapper.toDTO (customerRegistration, CustomerRegistrationDTO.class));
    }
}
