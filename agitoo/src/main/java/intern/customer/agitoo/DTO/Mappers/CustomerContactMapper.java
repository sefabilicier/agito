package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import org.springframework.stereotype.Component;

@Component
public class CustomerContactMapper extends
        GenericMapper<CustomerContact, CustomerContactDTO> {
}
