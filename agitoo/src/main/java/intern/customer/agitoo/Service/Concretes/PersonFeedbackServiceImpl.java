package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.DTO.Mappers.PersonFeedbackMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Repository.Abstracts.PersonFeedbackRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonFeedbackServiceImpl implements IPersonFeedbackService {

    @Autowired
    private PersonFeedbackRepository personFeedbackRepository;

    @Autowired
    private PersonFeedbackMapper personFeedbackMapper;


    @Override
    public List<PersonFeedbackDTO> getAll () {
        toDatabase.isConnected ();
        List<PersonFeedback> personFeedbacks = personFeedbackRepository.findAll ();
        List<PersonFeedbackDTO> personFeedbackDTOS = personFeedbacks
                .stream ()
                .map (personFeedback -> personFeedbackMapper
                        .toDTO (personFeedback, PersonFeedbackDTO.class))
                .collect(Collectors.toList ());
        return personFeedbackDTOS;
    }

    @Override
    public PersonFeedbackDTO add (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper.toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savePersonFeedback = personFeedbackRepository.save (personFeedback);

        return personFeedbackMapper.toDTO (savePersonFeedback, PersonFeedbackDTO.class);
    }

    @Override
    public PersonFeedbackDTO update (PersonFeedbackDTO dtoModel) {
        PersonFeedback personFeedback = personFeedbackMapper
                .toEntity (dtoModel, PersonFeedback.class);
        PersonFeedback savedPersonFeedback = personFeedbackRepository.save (personFeedback);

        return personFeedbackMapper.toDTO (savedPersonFeedback, PersonFeedbackDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        personFeedbackRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
