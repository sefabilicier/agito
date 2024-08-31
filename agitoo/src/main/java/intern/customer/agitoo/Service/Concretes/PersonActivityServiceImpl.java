package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.DTO.Mappers.PersonActivityMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.PersonActivity;
import intern.customer.agitoo.Repository.Abstracts.PersonActivityRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
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
    @Cacheable(value = "person-activity")
    public List<PersonActivityDTO> getAll () {
        isConnected ();
        List<PersonActivity> personActivities = personActivityRepository.findAll ();
        List<PersonActivityDTO> personActivityDTOS = personActivities
                .stream ()
                .map (personActivity -> personActivityMapper.toDTO (personActivity, PersonActivityDTO.class))
                .collect (Collectors.toList ());
        return personActivityDTOS;
    }

    @Override
    @CachePut(value = "person-activity", key = "")
    public PersonActivityDTO add (PersonActivityDTO dtoModel) {
        PersonActivity personActivity = personActivityMapper.toEntity (dtoModel, PersonActivity.class);
        PersonActivity savedPersonActivity = personActivityRepository.save (personActivity);
        return personActivityMapper.toDTO (savedPersonActivity, PersonActivityDTO.class);
    }

    @Override
    @CachePut(value = "person-activity", key = "")
    public PersonActivityDTO update (PersonActivityDTO dtoModel) {
        PersonActivity personActivity = personActivityMapper
                .toEntity (dtoModel, PersonActivity.class);
        PersonActivity updatedPersonActivity = personActivityRepository.save (personActivity);
        return personActivityMapper.toDTO (updatedPersonActivity, PersonActivityDTO.class);
    }

    @Override
    @CacheEvict(value = "person-activity", key = "#id")
    public void deleteById (Long id) {
        checkIfIdExist (personActivityRepository, id);
        personActivityRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}
