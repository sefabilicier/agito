package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
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
@RequestMapping("/api/person-activity")
public class PersonActivityController {

    @Autowired
    private IPersonActivityService personActivityService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<PersonActivityDTO>>>> getAll () {
        log.info ("Received request to list customer activities!");
        return personActivityService.getAll ().thenApply (
                personActivityDTOList -> {
                    DataResult<List<PersonActivityDTO>> response = new DataResult<> (
                            personActivityDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<PersonActivityDTO>>> Add (@RequestBody @Valid PersonActivityDTO personActivityDTO) {
        log.info ("Received request to add customer activity {}", personActivityDTO);
        return personActivityService.add (personActivityDTO).thenApply (
                savedPersonActivityDTO -> {
                    DataResult<PersonActivityDTO> response = new DataResult<> (
                            savedPersonActivityDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<PersonActivityDTO>>> Update (@RequestBody @Valid PersonActivityDTO personActivityDTO) {
        log.info ("Received request to update customer activity {}", personActivityDTO);
        return personActivityService.update (personActivityDTO).thenApply (
                updatedPersonActivityDTO -> {
                    DataResult<PersonActivityDTO> response = new DataResult<> (
                            updatedPersonActivityDTO, true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer activity {}", id);
        return personActivityService.deleteById (id)
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
