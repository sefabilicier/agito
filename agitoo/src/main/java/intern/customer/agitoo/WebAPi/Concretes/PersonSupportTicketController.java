package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Helper.Messages;
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

@Slf4j
@RestController
@RequestMapping("/api/person-support-ticket")
public class PersonSupportTicketController {

    @Autowired
    private IPersonSupportTicketService personSupportTicketService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonSupportTicketDTO>>> getAll () {
        log.info ("Received request to list person support tickets!");
        List<PersonSupportTicketDTO> personSupportTicketDTOList = personSupportTicketService.getAll ();
        DataResult<List<PersonSupportTicketDTO>> response = new DataResult<> (
                personSupportTicketDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonSupportTicketDTO>> Add (@RequestBody @Valid PersonSupportTicketDTO personSupportTicketDTO) {
        log.info ("Received request to add person support ticket {}", personSupportTicketDTO);
        PersonSupportTicketDTO addedPersonSupportTicketDTO = personSupportTicketService.add (personSupportTicketDTO);
        DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                addedPersonSupportTicketDTO, true, Messages.ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonSupportTicketDTO>> Update (@RequestBody @Valid PersonSupportTicketDTO personSupportTicketDTO) {
        log.info ("Received request to update person support ticket {}", personSupportTicketDTO);
        PersonSupportTicketDTO updatedPersonSupportTicketDTO = personSupportTicketService.update (
                personSupportTicketDTO
        );
        DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                updatedPersonSupportTicketDTO,

                true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person support ticket {}", id);
        this.personSupportTicketService.deleteById (id);
    }
}
