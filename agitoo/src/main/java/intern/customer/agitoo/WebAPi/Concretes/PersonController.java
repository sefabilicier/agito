package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonDTO>>> getAll () {
        log.info("Received request to list all person!");
        List<PersonDTO> personDTOList = personService.getAll ();
        DataResult<List<PersonDTO>> response = new DataResult<> (
                personDTOList,
                true,
                "Person listed"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonDTO>> Add (PersonDTO personDTO) {
        log.info("Received request to add person {}", personDTO);
        PersonDTO addedPersonDTO = personService.add (personDTO);
        DataResult<PersonDTO> response = new DataResult<> (
                addedPersonDTO, true, "Person added!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonDTO>> Update (PersonDTO personDTO) {
        log.info("Received request to update person {}", personDTO);
        PersonDTO updatedPersonDTO = personService.update (personDTO);
        DataResult<PersonDTO> response = new DataResult<> (
                updatedPersonDTO,
                true, "Person updated! "
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete person {}", id);
        this.personService.deleteById (id);
    }
}
