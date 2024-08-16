package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Repository.Abstracts.PersonFeedbackRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonFeedbackServiceImpl implements IPersonFeedbackService {

    @Autowired
    private PersonFeedbackRepository personFeedbackRepository;

    @Override
    public DataResult<List<PersonFeedback>> getAll () {
        return new SuccessDataResult<List<PersonFeedback>> (
                personFeedbackRepository.findAll (),
                "Customer feedbacks listed!"
        );
    }

    @Override
    public Result Add (PersonFeedback entity) {
        personFeedbackRepository.save (entity);
        return new SuccessResult (
                true,
                "Customer feedback added!"
        );
    }

    @Override
    public Result Update (PersonFeedback entity) {
        if (personFeedbackRepository.existsById (entity.getFeedbackId ())) {
            personFeedbackRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Customer feedback successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer feedback not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (personFeedbackRepository.existsById (id)) {
            personFeedbackRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Customer feedback removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Customer feedback not found"
            );
        }
    }
}
