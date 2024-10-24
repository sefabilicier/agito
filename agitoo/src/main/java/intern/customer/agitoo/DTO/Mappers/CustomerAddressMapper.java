package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressMapper extends
        GenericMapper<CustomerAddress, CustomerAddressDTO> {

    public CustomerAddressMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
