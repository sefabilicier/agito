package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/person-activity")
public class PersonActivityController {

    @Autowired
    private IPersonActivityService personActivityService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonActivityDTO>>> getAll () {
        log.info("Received request to list customer activities!");
        List<PersonActivityDTO> personActivityDTOList = personActivityService.getAll ();
        DataResult<List<PersonActivityDTO>> response = new DataResult<> (
                personActivityDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonActivityDTO>> Add (@RequestBody @Valid PersonActivityDTO personActivityDTO) {
        log.info("Received request to add customer activity {}", personActivityDTO);
        PersonActivityDTO savedPersonActivityDTO = personActivityService.add (personActivityDTO);
        DataResult<PersonActivityDTO> response = new DataResult<>(
                savedPersonActivityDTO, true, Messages.ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonActivityDTO>> Update (@RequestBody @Valid PersonActivityDTO personActivityDTO) {
        log.info("Received request to update customer activity {}", personActivityDTO);
        PersonActivityDTO updatedPersonActivityDTO = personActivityService.update (personActivityDTO);
        DataResult<PersonActivityDTO> response = new DataResult<> (
                updatedPersonActivityDTO, true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer activity {}", id);
        this.personActivityService.deleteById (id);
    }
}
