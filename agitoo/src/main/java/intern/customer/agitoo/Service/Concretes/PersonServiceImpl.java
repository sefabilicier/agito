package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.Mappers.PersonMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Repository.Abstracts.PersonRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
import intern.customer.agitoo.Service.Rules.CommonBusinessRules;
import intern.customer.agitoo.Service.Rules.PersonBusinessRules;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonBusinessRules personBusinessRules;


    @Override
    @Cacheable(value = "person")
    public List<PersonDTO> getAll () {
        toDatabase.isConnected ();
        List<Person> personList = personRepository.findAll ();
        List<PersonDTO> personDTOS = personList
                .stream ()
                .map (person -> personMapper
                        .toDTO (person, PersonDTO.class))
                .collect(Collectors.toList ());
        return personDTOS;
    }

    @Override
    @CachePut(value = "person", key = "")
    public PersonDTO add (PersonDTO dtoModel) {

        personBusinessRules.checkIfPersonFullNameExists (dtoModel.getFirstName ());
        Person personList = personMapper.toEntity (dtoModel, Person.class);
        Person savedPerson = personRepository.save(personList);

        return personMapper.toDTO (savedPerson, PersonDTO.class);
    }

    @Override
    @CachePut(value = "person", key = "")
    public PersonDTO update (PersonDTO dtoModel) {
        Person personList = personMapper
                .toEntity (dtoModel, Person.class);
        Person updatedPerson = personRepository.save (personList);
        return personMapper.toDTO (updatedPerson, PersonDTO.class);
    }

    @Override
    @CacheEvict(value = "person", key = "#id")
    public void deleteById (Long id) {
        CommonBusinessRules.checkIfIdExist (personRepository, id);
        personRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}