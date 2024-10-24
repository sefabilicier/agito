package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonFeedbackMapper extends
        GenericMapper<PersonFeedback, PersonFeedbackDTO> {

    public PersonFeedbackMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
