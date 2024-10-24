package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
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
@RequestMapping("/api/person-feedback")
public class PersonFeedbackController {

    @Autowired
    private IPersonFeedbackService personFeedbackService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<PersonFeedbackDTO>>>> getAll () {
        log.info ("Received request to list person feedbacks!");
        return personFeedbackService.getAll ().thenApply (
                personFeedbackDTOList -> {
                    DataResult<List<PersonFeedbackDTO>> response = new DataResult<> (
                            personFeedbackDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<PersonFeedbackDTO>>> Add (@RequestBody @Valid PersonFeedbackDTO personFeedbackDTO) {
        log.info ("Received request to add person feedback {}", personFeedbackDTO);
        return personFeedbackService.add (personFeedbackDTO).thenApply (
                addedPersonFeedbackDTO -> {
                    DataResult<PersonFeedbackDTO> response = new DataResult<> (
                            addedPersonFeedbackDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<PersonFeedbackDTO>>> Update (@RequestBody @Valid PersonFeedbackDTO personFeedbackDTO) {
        log.info ("Received request to update person feedback {}", personFeedbackDTO);
        return personFeedbackService.update (
                personFeedbackDTO
        ).thenApply (
                updatedPersoFeedbackDTO -> {
                    DataResult<PersonFeedbackDTO> response = new DataResult<> (
                            updatedPersoFeedbackDTO,
                            true, UPDATED
                    );

                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person feedback {}", id);
        return personFeedbackService.deleteById (id)
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
