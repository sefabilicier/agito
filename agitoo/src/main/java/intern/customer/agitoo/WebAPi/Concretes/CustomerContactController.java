package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
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
@RequestMapping("/api/customer-contact")
public class CustomerContactController {

    @Autowired
    private ICustomerContactService customerContactService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerContactDTO>>> getAll () {
        log.info ("Received request to list customer contacts! ");
        List<CustomerContactDTO> customerContactDTOList = customerContactService.getAll ();
        DataResult<List<CustomerContactDTO>> response = new DataResult<> (
                customerContactDTOList, true, Messages.LISTED);
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerContactDTO>> Add (@RequestBody @Valid CustomerContactDTO customerContactDTO) {
        log.info ("Received request to create customer contact {}", customerContactDTO);
        CustomerContactDTO customerContact = customerContactService.add (customerContactDTO);
        DataResult<CustomerContactDTO> response = new DataResult<> (
                customerContact,
                true,
                Messages.ADDED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerContactDTO>> Update (@RequestBody @Valid CustomerContactDTO customerContactDTO) {
        log.info("Received request to update customer contact {}", customerContactDTO);
        CustomerContactDTO customerContact = customerContactService.add (customerContactDTO);
        DataResult<CustomerContactDTO> response = new DataResult<> (
                customerContact, true,  Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer contact {}", id);
        this.customerContactService.deleteById (id);
    }
}