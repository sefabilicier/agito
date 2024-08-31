package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.DTO.Mappers.PersonFeedbackMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Repository.Abstracts.PersonFeedbackRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Cacheable(value = "person-feedback")
    public List<PersonFeedbackDTO> getAll () {
        isConnected ();
        List<PersonFeedback> personFeedbacks = personFeedbackRepository.findAll ();
        List<PersonFeedbackDTO> personFeedbackDTOS = personFeedbacks
                .stream ()
                .map (personFeedback -> personFeedbackMapper
                        .toDTO (personFeedback, PersonFeedbackDTO.class))
                .collect (Collectors.toList ());
        return personFeedbackDTOS;
    }

    @Override
    @CachePut(value = "person-feedback", key = "")
    public PersonFeedbackDTO add (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper.toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savePersonFeedback = personFeedbackRepository.save (personFeedback);

        return personFeedbackMapper.toDTO (savePersonFeedback, PersonFeedbackDTO.class);
    }

    @Override
    @CachePut(value = "person-feedback", key = "")
    public PersonFeedbackDTO update (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper
                .toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savedPersonFeedback = personFeedbackRepository.save (personFeedback);

        return personFeedbackMapper.toDTO (savedPersonFeedback, PersonFeedbackDTO.class);
    }

    @Override
    @CacheEvict(value = "person-feedback", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (personFeedbackRepository, id);
        personFeedbackRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
