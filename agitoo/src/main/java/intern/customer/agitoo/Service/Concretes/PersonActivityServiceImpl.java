package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.DTO.Mappers.PersonActivityMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonActivity;
import intern.customer.agitoo.Repository.Abstracts.PersonActivityRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
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
public class PersonActivityServiceImpl implements IPersonActivityService {

    @Autowired
    private PersonActivityRepository personActivityRepository;

    @Autowired
    private PersonActivityMapper personActivityMapper;


    @Override
    @Async
    @Transactional(readOnly = true)
    @Cacheable(value = "person-activity")
    public CompletableFuture<List<PersonActivityDTO>> getAll () {
        isConnected ();
        List<PersonActivity> personActivities = personActivityRepository.findAll ();
        List<PersonActivityDTO> personActivityDTOS = personActivities
                .stream ()
                .map (personActivity -> personActivityMapper.toDTO (personActivity, PersonActivityDTO.class))
                .collect (Collectors.toList ());
        return CompletableFuture.completedFuture (personActivityDTOS);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-activity", key = "#result.activityId")
    public CompletableFuture<PersonActivityDTO> add (PersonActivityDTO dtoModel) {
        PersonActivity personActivity = personActivityMapper.toEntity (dtoModel, PersonActivity.class);
        PersonActivity savedPersonActivity = personActivityRepository.save (personActivity);
        return CompletableFuture.completedFuture (personActivityMapper.toDTO (savedPersonActivity, PersonActivityDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-activity", key = "#result.activityId")
    public CompletableFuture<PersonActivityDTO> update (PersonActivityDTO dtoModel) {
        PersonActivity personActivity = personActivityMapper
                .toEntity (dtoModel, PersonActivity.class);
        PersonActivity updatedPersonActivity = personActivityRepository.save (personActivity);
        return CompletableFuture.completedFuture (personActivityMapper.toDTO (updatedPersonActivity, PersonActivityDTO.class));
    }

    @Override
    @Async
    @Transactional
    @CacheEvict(value = "person-activity", key = "#id")
    public CompletableFuture<Void> deleteById (Long id) {
        checkIfIdExist (personActivityRepository, id);
        personActivityRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
        return CompletableFuture.completedFuture (null);
    }

    @Override
    @Async
    @Transactional
    @CachePut(value = "person-activity", key = "#id")
    public CompletableFuture<PersonActivityDTO> findById (Long id) {
        checkIfIdExist (personActivityRepository, id);
        PersonActivity personActivity = personActivityRepository.findById (id)
                .orElseThrow (
                        () -> new EntityNotFoundException ("Person activity not found with id: " + id));
        return CompletableFuture.completedFuture (personActivityMapper.toDTO (personActivity, PersonActivityDTO.class));
    }
}
