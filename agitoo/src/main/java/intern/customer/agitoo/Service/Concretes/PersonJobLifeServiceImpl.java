package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.DTO.Mappers.PersonJobLifeMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import intern.customer.agitoo.Repository.Abstracts.PersonJobLifeRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
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
public class PersonJobLifeServiceImpl implements IPersonJobLifeService {

    @Autowired
    private PersonJobLifeRepository personJobLifeRepository;

    @Autowired
    private PersonJobLifeMapper personJobLifeMapper;

    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "person-job-life")
    public CompletableFuture<List<PersonJobLifeDTO>> getAll () {
        isConnected ();
        List<PersonJobLife> personJobLives = personJobLifeRepository.findAll ();
        List<PersonJobLifeDTO> personJobLifeDTOS = personJobLives
                .stream ()
                .map (personJobLife -> personJobLifeMapper
                        .toDTO (personJobLife, PersonJobLifeDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (personJobLifeDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-job-life", key = "#result.jobID")
    public CompletableFuture<PersonJobLifeDTO> add (PersonJobLifeDTO dtoModel) {
        PersonJobLife personJobLife = personJobLifeMapper.toEntity (dtoModel, PersonJobLife.class);
        PersonJobLife savedPersonJobLife = personJobLifeRepository.save (personJobLife);

        return CompletableFuture.completedFuture (personJobLifeMapper.toDTO (savedPersonJobLife, PersonJobLifeDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-job-life", key = "#result.jobID")
    public CompletableFuture<PersonJobLifeDTO> update (PersonJobLifeDTO dtoModel) {
        PersonJobLife personJobLife = personJobLifeMapper
                .toEntity (dtoModel, PersonJobLife.class);
        PersonJobLife updatedPersonJobLife = personJobLifeRepository.save (personJobLife);
        return CompletableFuture.completedFuture (personJobLifeMapper.toDTO (updatedPersonJobLife, PersonJobLifeDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "person-job-life", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (personJobLifeRepository, id);
        personJobLifeRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-job-life", key = "#id")
    public CompletableFuture<PersonJobLifeDTO> findById (Long id) {
        checkIfIdExist (personJobLifeRepository, id);
        PersonJobLife personJobLife = personJobLifeRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Person feedback not found with id: " + id));
        return CompletableFuture.completedFuture (personJobLifeMapper.toDTO (personJobLife, PersonJobLifeDTO.class));
    }
}