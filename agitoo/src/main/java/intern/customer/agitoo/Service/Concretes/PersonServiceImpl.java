package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Repository.Abstracts.PersonRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public DataResult<List<Person>> getAll () {
        return new SuccessDataResult<List<Person>> (
                personRepository.findAll (),
                "Person listed!"
        );
    }

    @Override
    public Result Add (Person entity) {
        personRepository.save (entity);
        return new SuccessResult (
                true,
                "Person added!"
        );
    }

    @Override
    public Result Update (Person entity) {
        if (personRepository.existsById (entity.getPersonId ())) {
            personRepository.save (entity);
            return new SuccessResult (
                    true,
                    "Person successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (personRepository.existsById (id)) {
            personRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    "Person removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "Person not found"
            );
        }
    }
}
