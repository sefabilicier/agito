package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.DTO.Mappers.PersonFeedbackMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Repository.Abstracts.PersonFeedbackRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
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
public class PersonFeedbackServiceImpl implements IPersonFeedbackService {

    @Autowired
    private PersonFeedbackRepository personFeedbackRepository;

    @Autowired
    private PersonFeedbackMapper personFeedbackMapper;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "person-feedback")
    public CompletableFuture<List<PersonFeedbackDTO>> getAll () {
        isConnected ();
        List<PersonFeedback> personFeedbacks = personFeedbackRepository.findAll ();
        List<PersonFeedbackDTO> personFeedbackDTOS = personFeedbacks
                .stream ()
                .map (personFeedback -> personFeedbackMapper
                        .toDTO (personFeedback, PersonFeedbackDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (personFeedbackDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-feedback", key = "#result.feedbackId")
    public CompletableFuture<PersonFeedbackDTO> add (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper.toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savePersonFeedback = personFeedbackRepository.save (personFeedback);

        return CompletableFuture.completedFuture (personFeedbackMapper.toDTO (savePersonFeedback, PersonFeedbackDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-feedback", key = "#result.feedbackId")
    public CompletableFuture<PersonFeedbackDTO> update (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper
                .toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savedPersonFeedback = personFeedbackRepository.save (personFeedback);

        return CompletableFuture.completedFuture (personFeedbackMapper.toDTO (savedPersonFeedback, PersonFeedbackDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "person-feedback", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (personFeedbackRepository, id);
        personFeedbackRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-feedback", key = "#id")
    public CompletableFuture<PersonFeedbackDTO> findById (Long id) {
        checkIfIdExist (personFeedbackRepository, id);
        PersonFeedback personFeedback = personFeedbackRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Person feedback not found with id: " + id));
        return CompletableFuture.completedFuture (personFeedbackMapper.toDTO (personFeedback, PersonFeedbackDTO.class));
    }
}
