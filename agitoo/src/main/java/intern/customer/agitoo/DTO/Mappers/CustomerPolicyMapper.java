package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import org.springframework.stereotype.Component;

@Component
public class CustomerPolicyMapper extends
        GenericMapper<CustomerPolicy, CustomerPolicyDTO> {
}
