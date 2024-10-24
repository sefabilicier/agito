package intern.customer.agitoo.DTO.Mappers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericMapper<S, D> {

    private final ModelMapper modelMapper;

    @Autowired
    public GenericMapper (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    //variable = (condition) ? expressionTrue :  expressionFalse;

    public D toDTO (S source, Class<D> destinationClass) {
        return source == null ? null : modelMapper.map (source, destinationClass);
    }

    public S toEntity (D destination, Class<S> sourceClass) {
        return destination == null ? null : modelMapper.map (destination, sourceClass);
    }
}














































