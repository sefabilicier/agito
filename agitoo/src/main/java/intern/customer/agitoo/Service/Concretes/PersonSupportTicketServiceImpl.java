package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.DTO.Mappers.PersonSupportTicketMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import intern.customer.agitoo.Repository.Abstracts.PersonSupportTicketRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Service.Rules.CommonBusinessRules.checkIfIdExist;
import static intern.customer.agitoo.Service.Rules.toDatabase.isConnected;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonSupportTicketServiceImpl implements IPersonSupportTicketService {

    @Autowired
    private PersonSupportTicketRepository personSupportTicketRepository;

    @Autowired
    private PersonSupportTicketMapper personSupportTicketMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "person-support-ticket")
    public CompletableFuture<List<PersonSupportTicketDTO>> getAll () {
        isConnected ();
        List<PersonSupportTicket> personSupportTickets = personSupportTicketRepository.findAll ();
        List<PersonSupportTicketDTO> personSupportTicketDTOS = personSupportTickets
                .stream ()
                .map (personSupportTicket -> personSupportTicketMapper
                        .toDTO (personSupportTicket, PersonSupportTicketDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (personSupportTicketDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-support-ticket", key = "#result.ticketID")
    public CompletableFuture<PersonSupportTicketDTO> add (PersonSupportTicketDTO dtoModel) {
        PersonSupportTicket personSupportTicket = personSupportTicketMapper.toEntity (dtoModel, PersonSupportTicket.class);
        PersonSupportTicket savedPersonSupportTicket = personSupportTicketRepository.save (personSupportTicket);

        return CompletableFuture.completedFuture (personSupportTicketMapper.toDTO (savedPersonSupportTicket, PersonSupportTicketDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-support-ticket", key = "#result.ticketID")
    public CompletableFuture<PersonSupportTicketDTO> update (PersonSupportTicketDTO dtoModel) {
        PersonSupportTicket personSupportTicket = personSupportTicketMapper
                .toEntity (dtoModel, PersonSupportTicket.class);
        PersonSupportTicket updatedPersonSupportTicket = personSupportTicketRepository.save (personSupportTicket);
        return CompletableFuture.completedFuture (personSupportTicketMapper.toDTO (updatedPersonSupportTicket, PersonSupportTicketDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "person-support-ticket", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (personSupportTicketRepository, id);
        personSupportTicketRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-feedback", key = "#id")
    public CompletableFuture<PersonSupportTicketDTO> findById (Long id) {
        checkIfIdExist (personSupportTicketRepository, id);
        PersonSupportTicket personSupportTicket = personSupportTicketRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Person support ticket not found with id: " + id));
        return CompletableFuture.completedFuture (personSupportTicketMapper.toDTO (personSupportTicket, PersonSupportTicketDTO.class));

    }
}
