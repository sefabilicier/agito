package intern.customer.agitoo.DTO.Mappers;

import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonSupportTicketMapper extends
        GenericMapper<PersonSupportTicket, PersonSupportTicketDTO> {

    public PersonSupportTicketMapper (ModelMapper modelMapper) {
        super (modelMapper);
    }
}
