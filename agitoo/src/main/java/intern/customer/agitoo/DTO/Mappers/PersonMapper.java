package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper extends
        GenericMapper<Person, PersonDTO> {
}
