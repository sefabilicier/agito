package intern.customer.agitoo.Service.Abstracts;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public interface IGenericService<DTO> {

    CompletableFuture<List<DTO>> getAll ();

    CompletableFuture<DTO> add (DTO dtoModel);

    CompletableFuture<DTO> update (DTO dtoModel);

    CompletableFuture<Void> deleteById (Long id);

    CompletableFuture<DTO> findById (Long id);
}