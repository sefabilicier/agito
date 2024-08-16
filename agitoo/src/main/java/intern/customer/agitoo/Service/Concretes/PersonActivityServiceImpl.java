package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.PersonActivity;
import intern.customer.agitoo.Repository.Abstracts.PersonActivityRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonActivityServiceImpl implements IPersonActivityService {

    @Autowired
    private PersonActivityRepository personActivityRepository;


    @Override
    public DataResult<List<PersonActivity>> getAll () {
        return new SuccessDataResult<List<PersonActivity>> (
                personActivityRepository.findAll (),
                "Person activities listed!"
        );
    }

    @Override
    public Result Add (PersonActivity entity) {
        personActivityRepository.save (entity);
        return new SuccessResult (
                true,
                "Person activity added!"
        );
    }

    @Override
    public Result Update (PersonActivity entity) {
        if (personActivityRepository.existsById (entity.getActivityId ())) {
            personActivityRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Person activity successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person activity not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (personActivityRepository.existsById (id)) {
            personActivityRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Person activity removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person activity not found"
            );
        }
    }
}
