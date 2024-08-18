package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper extends
        GenericMapper<Customer, CustomerDTO> {
}
