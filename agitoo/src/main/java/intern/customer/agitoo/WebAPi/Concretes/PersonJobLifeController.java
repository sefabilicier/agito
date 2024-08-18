package java.intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
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
@RequestMapping("/api/personjoblife")
public class PersonJobLifeController {

    @Autowired
    private IPersonJobLifeService personJobLifeService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<PersonJobLifeDTO>>> getAll () {
        log.info("Received request to add person job lives!");
        List<PersonJobLifeDTO> personJobLifeDTOList = personJobLifeService.getAll ();
        DataResult<List<PersonJobLifeDTO>> response = new DataResult<> (
                personJobLifeDTOList,
                true,
                "Person job lives listed!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<PersonJobLifeDTO>> Add (PersonJobLifeDTO personJobLifeDTO) {
        log.info("Received request to add person job life {}", personJobLifeDTO);
        PersonJobLifeDTO addedPersonJobLife = personJobLifeService.add (personJobLifeDTO);
        DataResult<PersonJobLifeDTO> response = new DataResult<> (
                addedPersonJobLife, true, "Person job life added!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<PersonJobLifeDTO>> Update (PersonJobLifeDTO personJobLifeDTO) {
        log.info("Received request to update person job life {}", personJobLifeDTO);
        PersonJobLifeDTO updatedPersonJobLifeDTO = personJobLifeService.update (
                personJobLifeDTO
        );
        DataResult<PersonJobLifeDTO> response = new DataResult<> (
                updatedPersonJobLifeDTO, true,
                "Person job life updated! "
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete person job life {}", id);
        this.personJobLifeService.deleteById (id);
    }
}
