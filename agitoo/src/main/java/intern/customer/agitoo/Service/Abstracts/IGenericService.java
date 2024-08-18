package intern.customer.agitoo.Service.Abstracts;

import java.util.List;

public interface IGenericService<DTO> {

    List<DTO> getAll ();
    DTO add (DTO dtoModel);
    DTO update (DTO dtoModel);
    void deleteById (Long id);

}