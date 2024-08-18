package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.DTO.Mappers.CustomerAddressMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Repository.Abstracts.CustomerAddressRepository;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressServiceImpl implements ICustomerAddressService {

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerAddressMapper customerAddressMapper;


    @Override
    public List<CustomerAddressDTO> getAll () {
        List<CustomerAddress> customerAddresses = customerAddressRepository.findAll ();
        List<CustomerAddressDTO> customerAddressDTOS = customerAddresses
                .stream ()
                .map (customerAddress -> customerAddressMapper
                        .toDTO (customerAddress, CustomerAddressDTO.class)).collect (Collectors.toList ());

        return customerAddressDTOS;
    }

    @Override
    public CustomerAddressDTO add (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper.toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress savedCustomerAddress = customerAddressRepository.save (customerAddress);
        return customerAddressMapper.toDTO (savedCustomerAddress, CustomerAddressDTO.class);
    }

    @Override
    public CustomerAddressDTO update (CustomerAddressDTO dtoModel) {
        CustomerAddress customerAddress = customerAddressMapper
                .toEntity (dtoModel, CustomerAddress.class);
        CustomerAddress updatedCustomerAddress = customerAddressRepository.save (customerAddress);
        return customerAddressMapper.toDTO (updatedCustomerAddress, CustomerAddressDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        customerAddressRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
