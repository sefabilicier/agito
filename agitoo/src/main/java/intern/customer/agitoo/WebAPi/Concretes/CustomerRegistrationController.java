package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
@RequestMapping("/api/customer-registration")
public class CustomerRegistrationController {

    @Autowired
    private ICustomerRegistrationService customerRegistrationService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerRegistrationDTO>>> getAll () {
        log.info("Received request to list customer registrations!");
        List<CustomerRegistrationDTO> customerRegistrationDTOList = customerRegistrationService.getAll ();
        DataResult<List<CustomerRegistrationDTO>> response = new DataResult<> (
                customerRegistrationDTOList,
                true,
                "Customer registrations listed!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerRegistrationDTO>> Add (CustomerRegistrationDTO customerRegistrationDTO) {
        log.info("Received request to add customer registration {}", customerRegistrationDTO);
        CustomerRegistrationDTO savedCustomerRegistrationDTO = customerRegistrationService.add (customerRegistrationDTO);
        DataResult<CustomerRegistrationDTO> response = new DataResult<>(
                savedCustomerRegistrationDTO, true, "Customer registration added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerRegistrationDTO>> Update (CustomerRegistrationDTO customerRegistrationDTO) {
        log.info("Received request to update customer registration {}", customerRegistrationDTO);
        CustomerRegistrationDTO updatedCustomerRegistrationDTO = customerRegistrationService.update (customerRegistrationDTO);
        DataResult<CustomerRegistrationDTO> response = new DataResult<> (
                updatedCustomerRegistrationDTO,
                true, "Customer registration updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer registration {}", id);
        this.customerRegistrationService.deleteById (id);
    }
}
