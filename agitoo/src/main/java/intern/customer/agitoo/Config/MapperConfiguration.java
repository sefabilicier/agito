package intern.customer.agitoo.Config;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public ModelMapper modelMapper () {
        ModelMapper modelMapper = new ModelMapper ();
        modelMapper.getConfiguration ()
                .setFieldMatchingEnabled (true) //a ModelMapper to allow PRIVATE fields to be matched:
                .setFieldAccessLevel (org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //a ModelMapper to allow PROTECTED fields to be matched - My data came with null and here is why
                .setMethodAccessLevel (org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //a ModelMapper to allow PROTECTED methods to be matched:
                .setSourceNamingConvention (NamingConventions.JAVABEANS_MUTATOR); //Finds eligible mutators according to JavaBeans convention

        /*modelMapper.typeMap(Person.class, PersonDTO.class).addMappings(mapper -> {
            mapper.map(src -> src.getFirstName() +
                            (src.getMiddleName() != null ? " " + src.getMiddleName() : "") +
                            " " + src.getLastName(),
                    PersonDTO::setFullName);
        });*/

        return modelMapper;
    }

}