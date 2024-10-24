package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
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
@RequestMapping("/api/person-support-ticket")
public class PersonSupportTicketController {

    @Autowired
    private IPersonSupportTicketService personSupportTicketService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<PersonSupportTicketDTO>>>> getAll () {
        log.info ("Received request to list person support tickets!");
        return personSupportTicketService.getAll ().thenApply (
                personSupportTicketDTOList -> {
                    DataResult<List<PersonSupportTicketDTO>> response = new DataResult<> (
                            personSupportTicketDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<PersonSupportTicketDTO>>> Add (@RequestBody @Valid PersonSupportTicketDTO personSupportTicketDTO) {
        log.info ("Received request to add person support ticket {}", personSupportTicketDTO);
        return personSupportTicketService.add (personSupportTicketDTO).thenApply (
                addedPersonSupportTicketDTO -> {
                    DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                            addedPersonSupportTicketDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<PersonSupportTicketDTO>>> Update (@RequestBody @Valid PersonSupportTicketDTO personSupportTicketDTO) {
        log.info ("Received request to update person support ticket {}", personSupportTicketDTO);
        return personSupportTicketService.update (
                personSupportTicketDTO
        ).thenApply (
                updatedPersonSupportTicketDTO -> {
                    DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                            updatedPersonSupportTicketDTO,

                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person support ticket {}", id);
        return personSupportTicketService.deleteById (id)
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
