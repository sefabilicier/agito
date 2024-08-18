package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
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
@RequestMapping("/api/personfeedback")
public class PersonFeedbackController {

    @Autowired
    private IPersonFeedbackService personFeedbackService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonFeedbackDTO>>> getAll () {
        log.info("Received request to list person feedbacks!");
        List<PersonFeedbackDTO> personFeedbackDTOList = personFeedbackService.getAll ();
        DataResult<List<PersonFeedbackDTO>> response = new DataResult<> (
                personFeedbackDTOList,
                true,
                "Person feedbacks listed!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonFeedbackDTO>> Add (PersonFeedbackDTO personFeedbackDTO) {
        log.info("Received request to add person feedback {}", personFeedbackDTO);
        PersonFeedbackDTO addedPersonFeedbackDTO = personFeedbackService.add (personFeedbackDTO);
        DataResult<PersonFeedbackDTO> response = new DataResult<> (
                addedPersonFeedbackDTO, true, "Person feedback added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonFeedbackDTO>> Update (PersonFeedbackDTO personFeedbackDTO) {
        log.info("Received request to update person feedback {}", personFeedbackDTO);
        PersonFeedbackDTO updatedPersoFeedbackDTO = personFeedbackService.update (
                personFeedbackDTO
        );
        DataResult<PersonFeedbackDTO> response = new DataResult<> (
                updatedPersoFeedbackDTO,
                true, "Customer feedback updated!"
        );

        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete person feedback {}", id);
        this.personFeedbackService.deleteById (id);
    }
}
