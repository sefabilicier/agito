package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.DTO.Mappers.PersonSupportTicketMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import intern.customer.agitoo.Repository.Abstracts.PersonSupportTicketRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonSupportTicketServiceImpl implements IPersonSupportTicketService {

    @Autowired
    private PersonSupportTicketRepository personSupportTicketRepository;

    @Autowired
    private PersonSupportTicketMapper personSupportTicketMapper;

    @Override
    public List<PersonSupportTicketDTO> getAll () {
        List<PersonSupportTicket> personSupportTickets = personSupportTicketRepository.findAll ();
        List<PersonSupportTicketDTO> personSupportTicketDTOS = personSupportTickets
                .stream ()
                .map (personSupportTicket -> personSupportTicketMapper
                        .toDTO (personSupportTicket, PersonSupportTicketDTO.class))
                .collect(Collectors.toList());
        return personSupportTicketDTOS;
    }

    @Override
    public PersonSupportTicketDTO add (PersonSupportTicketDTO dtoModel) {
        PersonSupportTicket personSupportTicket = personSupportTicketMapper.toEntity (dtoModel, PersonSupportTicket.class);
        PersonSupportTicket savedPersonSupportTicket = personSupportTicketRepository.save (personSupportTicket);

        return personSupportTicketMapper.toDTO (savedPersonSupportTicket, PersonSupportTicketDTO.class);
    }

    @Override
    public PersonSupportTicketDTO update (PersonSupportTicketDTO dtoModel) {
        PersonSupportTicket personSupportTicket = personSupportTicketMapper
                .toEntity (dtoModel, PersonSupportTicket.class);
        PersonSupportTicket updatedPersonSupportTicket = personSupportTicketRepository.save (personSupportTicket);
        return personSupportTicketMapper.toDTO (updatedPersonSupportTicket, PersonSupportTicketDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        personSupportTicketRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
