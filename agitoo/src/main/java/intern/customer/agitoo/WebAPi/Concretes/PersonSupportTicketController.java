package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("/api/person-support-ticket")
public class PersonSupportTicketController {

    @Autowired
    private IPersonSupportTicketService personSupportTicketService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonSupportTicketDTO>>> getAll () {
        log.info("Received request to list person support tickets!");
        List<PersonSupportTicketDTO> personSupportTicketDTOList = personSupportTicketService.getAll ();
        DataResult<List<PersonSupportTicketDTO>> response = new DataResult<> (
                personSupportTicketDTOList,
                true,
                "Person support tickets listed!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonSupportTicketDTO>> Add (PersonSupportTicketDTO personSupportTicketDTO) {
        log.info("Received request to add person support ticket {}", personSupportTicketDTO);
        PersonSupportTicketDTO addedPersonSupportTicketDTO = personSupportTicketService.add (personSupportTicketDTO);
        DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                addedPersonSupportTicketDTO, true, "Person support ticket added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonSupportTicketDTO>> Update (PersonSupportTicketDTO personSupportTicketDTO) {
        log.info("Received request to update person support ticket {}", personSupportTicketDTO);
        PersonSupportTicketDTO updatedPersonSupportTicketDTO = personSupportTicketService.update (
                personSupportTicketDTO
        );
        DataResult<PersonSupportTicketDTO> response = new DataResult<> (
                updatedPersonSupportTicketDTO,

            true, "Person support ticket updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete person support ticket {}", id);
        this.personSupportTicketService.deleteById (id);
    }
}
