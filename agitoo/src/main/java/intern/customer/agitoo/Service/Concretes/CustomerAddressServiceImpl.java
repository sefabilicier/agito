package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
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
public class CustomerAddressServiceImpl implements ICustomerAddressService {

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerAddressMapper customerAddressMapper;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-address")
    public CompletableFuture<List<CustomerAddressDTO>> getAll () {
        isConnected ();
        List<CustomerAddress> customerAddresses = customerAddressRepository.findAll ();
        List<CustomerAddressDTO> customerAddressDTOS = customerAddresses
                .stream ()
                .map (customerAddress -> customerAddressMapper
                        .toDTO (customerAddress, CustomerAddressDTO.class)).collect (Collectors.toList ());

        return CompletableFuture.completedFuture (customerAddressDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address", key = "#result.addressID")
    public CompletableFuture<CustomerAddressDTO> add (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper.toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress savedCustomerAddress = customerAddressRepository.save (customerAddress);
        return CompletableFuture.completedFuture (customerAddressMapper.toDTO (savedCustomerAddress, CustomerAddressDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address", key = "#result.addressID")
    public CompletableFuture<CustomerAddressDTO> update (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper
                .toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress updatedCustomerAddress = customerAddressRepository.save (customerAddress);
        return CompletableFuture.completedFuture (customerAddressMapper.toDTO (updatedCustomerAddress, CustomerAddressDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-address", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerAddressRepository, id);
        customerAddressRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-country", key = "#id")
    public CompletableFuture<CustomerAddressDTO> findById (Long id) {
        checkIfIdExist (customerAddressRepository, id);
        CustomerAddress customerAddress = customerAddressRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer Address not found with id: " + id));
        return CompletableFuture.completedFuture (customerAddressMapper.toDTO (customerAddress, CustomerAddressDTO.class));
    }
}
