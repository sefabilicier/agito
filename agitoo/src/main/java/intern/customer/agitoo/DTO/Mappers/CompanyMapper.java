package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.Models.Concretes.Company;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper extends
        GenericMapper<Company, CompanyDTO> {

    public CompanyMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}

