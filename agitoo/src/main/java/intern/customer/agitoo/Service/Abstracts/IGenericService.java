package intern.customer.agitoo.Service.Abstracts;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IGenericService<DTO> {

    List<DTO> getAll ();
    DTO add (DTO dtoModel);
    DTO update (DTO dtoModel);
    void deleteById (Long id);

}