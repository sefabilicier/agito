package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import org.springframework.stereotype.Component;

@Component
public class CustomerPaymentMapper extends
        GenericMapper<CustomerPayment, CustomerPaymentDTO> {
}
