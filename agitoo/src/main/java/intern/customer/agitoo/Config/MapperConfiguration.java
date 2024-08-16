package intern.customer.agitoo.Config;

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
                .setMethodAccessLevel (org.modelmapper.config.Configuration.AccessLevel.PRIVATE) //a ModelMapper to allow PROTECTED methods to be matched:
                .setSourceNamingConvention (NamingConventions.JAVABEANS_MUTATOR); //Finds eligible mutators according to JavaBeans convention

        return modelMapper;
    }


}
