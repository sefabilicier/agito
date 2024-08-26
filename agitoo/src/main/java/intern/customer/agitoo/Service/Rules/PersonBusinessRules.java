package intern.customer.agitoo.Service.Rules;

import intern.customer.agitoo.Common.Exception.BusinessException;
import intern.customer.agitoo.Repository.Abstracts.CustomerRepository;
import intern.customer.agitoo.Repository.Abstracts.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PersonBusinessRules {

    private PersonRepository personRepository;

    public void checkIfPersonFullNameExists(String name) {
        if (personRepository.existsByFirstName(name))
            throw new BusinessException ("Person name already exists");
    }
}