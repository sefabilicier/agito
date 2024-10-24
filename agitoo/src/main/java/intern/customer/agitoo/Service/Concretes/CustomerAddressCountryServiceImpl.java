package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressCountryMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCountryRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
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
public class CustomerAddressCountryServiceImpl implements ICustomerAddressCountryService {

    @Autowired
    private CustomerAddressCountryRepository customerAddressCountryRepository;

    @Autowired
    private CustomerAddressCountryMapper customerAddressCountryMapper;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-address-country")
    public CompletableFuture<List<CustomerAddressCountryDTO>> getAll () {
        isConnected ();
        List<CustomerAddressCountry> customerAddressCountries = customerAddressCountryRepository.findAll ();
        List<CustomerAddressCountryDTO> customerAddressCountryDTOS = customerAddressCountries
                .stream ()
                .map (customerAddressCountry -> customerAddressCountryMapper
                        .toDTO (customerAddressCountry, CustomerAddressCountryDTO.class)).collect (Collectors.toList ());
        return CompletableFuture.completedFuture (customerAddressCountryDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-country", key = "#result.addressCountryId")
    public CompletableFuture<CustomerAddressCountryDTO> add (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper.toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return CompletableFuture.completedFuture (customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-country", key = "#result.addressCountryId")
    public CompletableFuture<CustomerAddressCountryDTO> update (CustomerAddressCountryDTO dtoModel) {
        CustomerAddressCountry customerAddressCountry = customerAddressCountryMapper
                .toEntity (dtoModel, CustomerAddressCountry.class);
        CustomerAddressCountry savedCustomerAddressCountry = customerAddressCountryRepository.save (customerAddressCountry);
        return CompletableFuture.completedFuture (customerAddressCountryMapper.toDTO (savedCustomerAddressCountry, CustomerAddressCountryDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-address-country", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerAddressCountryRepository, id);
        customerAddressCountryRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-country", key = "#id")
    public CompletableFuture<CustomerAddressCountryDTO> findById (Long id) {
        checkIfIdExist (customerAddressCountryRepository, id);
        CustomerAddressCountry customerAddressCountry = customerAddressCountryRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer Address not found with id: " + id));
        return CompletableFuture.completedFuture (customerAddressCountryMapper.toDTO (customerAddressCountry, CustomerAddressCountryDTO.class));
    }
}


