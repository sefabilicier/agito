package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import intern.customer.agitoo.Repository.Abstracts.PersonSupportTicketRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonSupportTicketServiceImpl implements IPersonSupportTicketService {

    @Autowired
    private PersonSupportTicketRepository personSupportTicketRepository;


    @Override
    public DataResult<List<PersonSupportTicket>> getAll () {
        return new SuccessDataResult<List<PersonSupportTicket>> (
                personSupportTicketRepository.findAll (),
                "Person support tickets tickets listed!"
        );
    }

    @Override
    public Result Add (PersonSupportTicket entity) {
        personSupportTicketRepository.save (entity);
        return new SuccessResult (
                true,
                "Person support ticket added!"
        );
    }

    @Override
    public Result Update (PersonSupportTicket entity) {
        if (personSupportTicketRepository.existsById (entity.getTicketID ())) {
            personSupportTicketRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Person support ticket successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person support ticket not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (personSupportTicketRepository.existsById (id)) {
            personSupportTicketRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Person support ticket removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person support ticket not found"
            );
        }
    }
}
