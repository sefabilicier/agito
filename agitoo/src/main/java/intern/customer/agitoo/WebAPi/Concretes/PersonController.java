package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Helper.Messages;
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

@Slf4j
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonDTO>>> getAll () {
        log.info ("Received request to list all person!");
        List<PersonDTO> personDTOList = personService.getAll ();
        DataResult<List<PersonDTO>> response = new DataResult<> (
                personDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonDTO>> Add (@RequestBody @Valid PersonDTO personDTO) {
        log.info ("Received request to add person {}", personDTO);
        PersonDTO addedPersonDTO = personService.add (personDTO);
        DataResult<PersonDTO> response = new DataResult<> (
                addedPersonDTO, true, Messages.ADDED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonDTO>> Update (@RequestBody @Valid PersonDTO personDTO) {
        log.info ("Received request to update person {}", personDTO);
        PersonDTO updatedPersonDTO = personService.update (personDTO);
        DataResult<PersonDTO> response = new DataResult<> (
                updatedPersonDTO,
                true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete person {}", id);
        this.personService.deleteById (id);
    }
}
