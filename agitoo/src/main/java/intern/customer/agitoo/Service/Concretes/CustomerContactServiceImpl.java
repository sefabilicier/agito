package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerContactMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import intern.customer.agitoo.Repository.Abstracts.CustomerContactRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
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
public class CustomerContactServiceImpl implements ICustomerContactService {

    @Autowired
    private CustomerContactRepository customerContactRepository;

    @Autowired
    private CustomerContactMapper customerContactMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-contact")
    public CompletableFuture<List<CustomerContactDTO>> getAll () {
        isConnected ();
        List<CustomerContact> customerContacts = customerContactRepository.findAll ();
        List<CustomerContactDTO> customerContactDTOS = customerContacts
                .stream ()
                .map (customerContact -> customerContactMapper
                        .toDTO (customerContact, CustomerContactDTO.class)).collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerContactDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-contact", key = "#result.contactID")
    public CompletableFuture<CustomerContactDTO> add (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper.toEntity (dtoModel, CustomerContact.class);
        CustomerContact savedCustomerContact = customerContactRepository.save (customerContact);
        return CompletableFuture.completedFuture (customerContactMapper.toDTO (savedCustomerContact, CustomerContactDTO.class));

    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-contact", key = "#result.contactID")
    public CompletableFuture<CustomerContactDTO> update (CustomerContactDTO dtoModel) {
        CustomerContact customerContact = customerContactMapper
                .toEntity (dtoModel, CustomerContact.class);
        CustomerContact updatedCustomerContact = customerContactRepository.save (customerContact);
        return CompletableFuture.completedFuture (customerContactMapper.toDTO (updatedCustomerContact, CustomerContactDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-contact", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerContactRepository, id);
        customerContactRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-contact", key = "#id")
    public CompletableFuture<CustomerContactDTO> findById (Long id) {
        checkIfIdExist (customerContactRepository, id);
        CustomerContact customerContact = customerContactRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer Claim not found with id: " + id));
        return CompletableFuture.completedFuture (customerContactMapper.toDTO (customerContact, CustomerContactDTO.class));
    }
}
