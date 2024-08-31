package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Cacheable(value = "customer-address")
    public List<CustomerAddressDTO> getAll () {
        isConnected ();
        List<CustomerAddress> customerAddresses = customerAddressRepository.findAll ();
        List<CustomerAddressDTO> customerAddressDTOS = customerAddresses
                .stream ()
                .map (customerAddress -> customerAddressMapper
                        .toDTO (customerAddress, CustomerAddressDTO.class)).collect (Collectors.toList ());

        return customerAddressDTOS;
    }

    @Override
    @CachePut(value = "customer-address", key = "")
    public CustomerAddressDTO add (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper.toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress savedCustomerAddress = customerAddressRepository.save (customerAddress);
        return customerAddressMapper.toDTO (savedCustomerAddress, CustomerAddressDTO.class);
    }

    @Override
    @CachePut(value = "customer-address", key = "")
    public CustomerAddressDTO update (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper
                .toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress updatedCustomerAddress = customerAddressRepository.save (customerAddress);
        return customerAddressMapper.toDTO (updatedCustomerAddress, CustomerAddressDTO.class);
    }

    @Override
    @CacheEvict(value = "customer-address", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (customerAddressRepository, id);
        customerAddressRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
