package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressCityMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressCityRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
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
public class CustomerAddressCityServiceImpl implements ICustomerAddressCityService {

    @Autowired
    private CustomerAddressCityRepository customerAddressCityRepository;

    @Autowired
    private CustomerAddressCityMapper customerAddressCityMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "customer-address-city")
    public CompletableFuture<List<CustomerAddressCityDTO>> getAll () {
        isConnected ();
        List<CustomerAddressCity> customerAddressCities = customerAddressCityRepository.findAll ();
        List<CustomerAddressCityDTO> customerAddressCityDTOS = customerAddressCities
                .stream ()
                .map (customerAddressCity -> customerAddressCityMapper.toDTO (customerAddressCity, CustomerAddressCityDTO.class))
                .collect (Collectors.toList ());

        return CompletableFuture.completedFuture (customerAddressCityDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-city", key = "#result.cityID")
    public CompletableFuture<CustomerAddressCityDTO> add (CustomerAddressCityDTO dtoModel) {
        CustomerAddressCity customerAddressCity = customerAddressCityMapper.toEntity (dtoModel, CustomerAddressCity.class);
        CustomerAddressCity savedCustomerAddressCity = customerAddressCityRepository.save (customerAddressCity);
        return CompletableFuture.completedFuture (customerAddressCityMapper.toDTO (savedCustomerAddressCity, CustomerAddressCityDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-city", key = "#result.cityID")
    public CompletableFuture<CustomerAddressCityDTO> update (CustomerAddressCityDTO dtoModel) {
        CustomerAddressCity customerAddressCity = customerAddressCityMapper
                .toEntity (dtoModel, CustomerAddressCity.class);
        CustomerAddressCity udpatedCustomerAddressCity = customerAddressCityRepository.save (customerAddressCity);
        return CompletableFuture.completedFuture (customerAddressCityMapper.toDTO (udpatedCustomerAddressCity, CustomerAddressCityDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "customer-address-city", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (customerAddressCityRepository, id);
        customerAddressCityRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "customer-address-city", key = "#id")
    public CompletableFuture<CustomerAddressCityDTO> findById (Long id) {
        checkIfIdExist (customerAddressCityRepository, id);
        CustomerAddressCity customerAddressCity = customerAddressCityRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Customer Address not found with id: " + id));
        return CompletableFuture.completedFuture (customerAddressCityMapper.toDTO (customerAddressCity, CustomerAddressCityDTO.class));
    }
}

