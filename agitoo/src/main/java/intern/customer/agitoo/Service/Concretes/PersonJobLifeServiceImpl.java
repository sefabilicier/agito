package intern.customer.agitoo.Service.Concretes;

import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.DTO.Mappers.PersonJobLifeMapper;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import intern.customer.agitoo.Repository.Abstracts.PersonJobLifeRepository;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
import intern.customer.agitoo.Service.Rules.toDatabase;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class PersonJobLifeServiceImpl implements IPersonJobLifeService {

    @Autowired
    private PersonJobLifeRepository personJobLifeRepository;

    @Autowired
    private PersonJobLifeMapper personJobLifeMapper;

    @Override
    public List<PersonJobLifeDTO> getAll () {
        toDatabase.isConnected ();
        List<PersonJobLife> personJobLives = personJobLifeRepository.findAll ();
        List<PersonJobLifeDTO> personJobLifeDTOS = personJobLives
                .stream ()
                .map (personJobLife -> personJobLifeMapper
                        .toDTO (personJobLife, PersonJobLifeDTO.class))
                .collect(Collectors.toList());
        return personJobLifeDTOS;
    }

    @Override
    public PersonJobLifeDTO add (PersonJobLifeDTO dtoModel) {
        PersonJobLife personJobLife = personJobLifeMapper.toEntity (dtoModel, PersonJobLife.class);
        PersonJobLife savedPersonJobLife = personJobLifeRepository.save (personJobLife);

        return personJobLifeMapper.toDTO (savedPersonJobLife, PersonJobLifeDTO.class);
    }

    @Override
    public PersonJobLifeDTO update (PersonJobLifeDTO dtoModel) {
        PersonJobLife personJobLife = personJobLifeMapper
                .toEntity (dtoModel, PersonJobLife.class);
        PersonJobLife updatedPersonJobLife = personJobLifeRepository.save (personJobLife);
        return personJobLifeMapper.toDTO (updatedPersonJobLife, PersonJobLifeDTO.class);
    }

    @Override
    public void deleteById (Long id) {
        personJobLifeRepository.deleteById (id);
        System.out.print (id + " " + Messages.REMOVED);
    }
}