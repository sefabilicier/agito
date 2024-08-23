package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
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
@RestController //@Controller and @ResponseBody both in one
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerDTO>>> getAll () {
        log.info("Received request to list customers!");
        List<CustomerDTO> customerDTOList = customerService.getAll ();
        DataResult<List<CustomerDTO>> response = new DataResult<> (
                customerDTOList,
                true,
                Messages.LISTED
        );

        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerDTO>> Add (@RequestBody @Valid CustomerDTO customerDTO) {
        log.info("Received request to add customer {}", customerDTO);
        CustomerDTO savedCustomer = customerService.add (customerDTO);
        DataResult<CustomerDTO> response = new DataResult<> (
                savedCustomer,
                true,
                Messages.ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerDTO>> Update (@RequestBody @Valid CustomerDTO customerDTO) {
        log.info("Received request to update customer {}", customerDTO);

        CustomerDTO udpatedCustomer = customerService.update (customerDTO);
        DataResult<CustomerDTO> response = new DataResult<>(
                udpatedCustomer, true, Messages.UPDATED);
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer {}", id);
        this.customerService.deleteById (id);
    }
}

