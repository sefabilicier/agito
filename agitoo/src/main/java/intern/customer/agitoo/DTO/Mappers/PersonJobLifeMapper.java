package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonJobLifeMapper extends
        GenericMapper<PersonJobLife, PersonJobLifeDTO> {

    public PersonJobLifeMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
