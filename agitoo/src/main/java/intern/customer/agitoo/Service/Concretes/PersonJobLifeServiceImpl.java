package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.Core.Results.*;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import intern.customer.agitoo.Repository.Abstracts.PersonJobLifeRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonJobLifeServiceImpl implements IPersonJobLifeService {

    @Autowired
    private PersonJobLifeRepository personJobLifeRepository;


    @Override
    public DataResult<List<PersonJobLife>> getAll () {
        return new SuccessDataResult<List<PersonJobLife>> (
                personJobLifeRepository.findAll (),
                "Person job life listed!"
        );
    }

    @Override
    public Result Add (PersonJobLife entity) {
        personJobLifeRepository.save (entity);
        return new SuccessResult (
                true,
                "Person job added!"
        );
    }

    @Override
    public Result Update (PersonJobLife entity) {
        if (personJobLifeRepository.existsById (entity.getJobID ())) {
            personJobLifeRepository.save (entity);
            return new SuccessResult (
                    true,
                    "person job successfully updated!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "person job not found"
            );
        }
    }

    @Override
    public Result Delete (Long id) {
        if (personJobLifeRepository.existsById (id)) {
            personJobLifeRepository.deleteById (id);
            return new SuccessResult (
                    true,
                    " person job removed!"
            );
        } else {
            return new ErrorResult (
                    false,
                    "person job not found"
            );
        }
    }
}
