package intern.customer.agitoo.DTO.Mappers;

import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class GenericMapper<S, D> {

    /* generic as allinOne

     *  S -> Source ------------ Customer
     *
     *  D -> Destination ------- CustomerDTO

     * */

    @Autowired
    private ModelMapper modelMapper;


    public D toDTO (S source, Class<D> destinationClass) {
        return modelMapper.map (source, destinationClass);
    }

    public S toEntity (D destination, Class<S> sourceClass) {
        return modelMapper.map (destination, sourceClass);
    }
}














































