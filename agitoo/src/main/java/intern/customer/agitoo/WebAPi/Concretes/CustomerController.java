package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
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
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerDTO>>> getAll () {
        log.info("Received request to list customers!");
        List<CustomerDTO> customerDTOList = customerService.getAll ();
        DataResult<List<CustomerDTO>> response = new DataResult<> (
                customerDTOList,
                true,
                "Customers listed"
        );

        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerDTO>> Add (CustomerDTO customerDTO) {
        log.info("Received request to add customer {}", customerDTO);
        CustomerDTO savedCustomer = customerService.add (customerDTO);
        DataResult<CustomerDTO> response = new DataResult<> (
                savedCustomer,
                true,
                "Customer added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerDTO>> Update (CustomerDTO customerDTO) {
        log.info("Received request to update customer {}", customerDTO);

        CustomerDTO udpatedCustomer = customerService.update (customerDTO);
        DataResult<CustomerDTO> response = new DataResult<>(
                udpatedCustomer, true, "Customer updated");
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer {}", id);
        this.customerService.deleteById (id);
    }
}

