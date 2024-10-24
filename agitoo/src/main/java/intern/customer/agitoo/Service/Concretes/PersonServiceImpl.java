package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.Mappers.PersonMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Repository.Abstracts.PersonRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
import intern.customer.agitoo.Service.Rules.PersonBusinessRules;
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
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonBusinessRules personBusinessRules;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "person")
    public CompletableFuture<List<PersonDTO>> getAll () {
        isConnected ();
        List<Person> personList = personRepository.findAll ();
        List<PersonDTO> personDTOS = personList
                .stream ()
                .map (person -> personMapper
                        .toDTO (person, PersonDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (personDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person", key = "#result.personId")
    public CompletableFuture<PersonDTO> add (PersonDTO dtoModel) {

        personBusinessRules.checkIfPersonFullNameExists (dtoModel.getFirstName ());
        Person personList = personMapper.toEntity (dtoModel, Person.class);
        Person savedPerson = personRepository.save (personList);

        return CompletableFuture.completedFuture (personMapper.toDTO (savedPerson, PersonDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person", key = "#result.personId")
    public CompletableFuture<PersonDTO> update (PersonDTO dtoModel) {
        Person personList = personMapper
                .toEntity (dtoModel, Person.class);
        Person updatedPerson = personRepository.save (personList);
        return CompletableFuture.completedFuture (personMapper.toDTO (updatedPerson, PersonDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "person", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (personRepository, id);
        personRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person", key = "#id")
    public CompletableFuture<PersonDTO> findById (Long id) {
        checkIfIdExist (personRepository, id);
        Person person = personRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Person not found with id: " + id));
        return CompletableFuture.completedFuture (personMapper.toDTO (person, PersonDTO.class));
    }
}