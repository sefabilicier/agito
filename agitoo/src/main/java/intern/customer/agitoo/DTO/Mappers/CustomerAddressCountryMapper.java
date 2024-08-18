package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import org.springframework.stereotype.Component;

@Component
public class CustomerAddressCountryMapper extends
        GenericMapper<CustomerAddressCountry, CustomerAddressCountryDTO> {
}
