package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper extends
        GenericMapper<Person, PersonDTO> {

    public PersonMapper (ModelMapper modelMapper) {
        super (modelMapper);
//        modelMapper.addMappings(new PropertyMap<Person, PersonDTO>() {
//            @Override
//            protected void configure() {
//                map().setFullName(
//                        (source.getFirstName() != null ? source.getFirstName() : "") +
//                                (source.getMiddleName() != null ? " " + source.getMiddleName() : "") +
//                                (source.getLastName() != null ? " " + source.getLastName() : "")
//                );
//            }
//        });
    }
}