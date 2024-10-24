package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
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
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<PersonDTO>>>> getAll () {
        log.info ("Received request to list all person!");
        return personService.getAll ().thenApply (
                personDTOList -> {
                    DataResult<List<PersonDTO>> response = new DataResult<> (
                            personDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(
            value = "/add",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<PersonDTO>>> Add (@RequestBody @Valid PersonDTO personDTO) {
        log.info ("Received request to add person {}", personDTO);
        return personService.add (personDTO).thenApply (
                addedPersonDTO -> {
                    DataResult<PersonDTO> response = new DataResult<> (
                            addedPersonDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<PersonDTO>>> Update (@RequestBody @Valid PersonDTO personDTO) {
        log.info ("Received request to update person {}", personDTO);
        return personService.update (personDTO).thenApply (
                updatedPersonDTO -> {
                    DataResult<PersonDTO> response = new DataResult<> (
                            updatedPersonDTO,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person {}", id);
        return personService.deleteById (id)
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
