package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerClaimMapper extends
        GenericMapper<CustomerClaim, CustomerClaimDTO> {

    public CustomerClaimMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
