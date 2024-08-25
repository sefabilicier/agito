package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
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
@RequestMapping("/api/customer-registration")
public class CustomerRegistrationController {

    @Autowired
    private ICustomerRegistrationService customerRegistrationService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerRegistrationDTO>>> getAll () {
        log.info("Received request to list customer registrations!");
        List<CustomerRegistrationDTO> customerRegistrationDTOList = customerRegistrationService.getAll ();
        DataResult<List<CustomerRegistrationDTO>> response = new DataResult<> (
                customerRegistrationDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerRegistrationDTO>> Add (@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO) {
        log.info("Received request to add customer registration {}", customerRegistrationDTO);
        CustomerRegistrationDTO savedCustomerRegistrationDTO = customerRegistrationService.add (customerRegistrationDTO);
        DataResult<CustomerRegistrationDTO> response = new DataResult<>(
                savedCustomerRegistrationDTO, true, Messages.ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerRegistrationDTO>> Update (@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO) {
        log.info("Received request to update customer registration {}", customerRegistrationDTO);
        CustomerRegistrationDTO updatedCustomerRegistrationDTO = customerRegistrationService.update (customerRegistrationDTO);
        DataResult<CustomerRegistrationDTO> response = new DataResult<> (
                updatedCustomerRegistrationDTO,
                true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer registration {}", id);
        this.customerRegistrationService.deleteById (id);
    }
}
