package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Models.Concretes.PersonActivity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonActivityMapper extends
        GenericMapper<PersonActivity, PersonActivityDTO> {

    public PersonActivityMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
