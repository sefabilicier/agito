package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import org.springframework.stereotype.Component;

@Component
public class CustomerClaimMapper extends
        GenericMapper<CustomerClaim, CustomerClaimDTO> {
}
