package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import org.springframework.stereotype.Component;

@Component
public class CustomerRegistrationMapper extends
        GenericMapper<CustomerRegistration, CustomerRegistrationDTO> {
}
