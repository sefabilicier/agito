package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/person-job-life")
public class PersonJobLifeController {

    @Autowired
    private IPersonJobLifeService personJobLifeService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<PersonJobLifeDTO>>>> getAll () {
        log.info ("Received request to add person job lives!");
        return personJobLifeService.getAll ().thenApply (
                personJobLifeDTOList -> {
                    DataResult<List<PersonJobLifeDTO>> response = new DataResult<> (
                            personJobLifeDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<PersonJobLifeDTO>>> Add (@RequestBody @Valid PersonJobLifeDTO personJobLifeDTO) {
        log.info ("Received request to add person job life {}", personJobLifeDTO);
        return personJobLifeService.add (personJobLifeDTO).thenApply (
                addedPersonJobLife -> {
                    DataResult<PersonJobLifeDTO> response = new DataResult<> (
                            addedPersonJobLife, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<PersonJobLifeDTO>>> Update (@RequestBody @Valid PersonJobLifeDTO personJobLifeDTO) {
        log.info ("Received request to update person job life {}", personJobLifeDTO);
        return personJobLifeService.update (
                personJobLifeDTO
        ).thenApply (
                updatedPersonJobLifeDTO -> {
                    DataResult<PersonJobLifeDTO> response = new DataResult<> (
                            updatedPersonJobLifeDTO, true,
                            UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person job life {}", id);
        return personJobLifeService.deleteById (id)
                .thenApply (result -> {
                    DataResult<Void> response = new DataResult<> (
                            result,
                            true,
                            REMOVED
                    );
                    return ResponseEntity.ok (response);
                });
    }
}
