package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/customer-payment")
public class CustomerPaymentController {

    @Autowired
    private ICustomerPaymentService customerPaymentService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerPaymentDTO>>> getAll () {
        log.info ("Received request to list customer payments!");
        List<CustomerPaymentDTO> customerPaymentDTOList = customerPaymentService.getAll ();
        DataResult<List<CustomerPaymentDTO>> response = new DataResult<> (
                customerPaymentDTOList,
                true,
                LISTED
        );

        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerPaymentDTO>> Add (@RequestBody @Valid CustomerPaymentDTO customerPaymentDTO) {
        log.info ("Received request to add customer payment {}", customerPaymentDTO);
        CustomerPaymentDTO savedCustomerPayment = customerPaymentService.add (customerPaymentDTO);
        DataResult<CustomerPaymentDTO> response = new DataResult<> (
                savedCustomerPayment, true, ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerPaymentDTO>> Update (@RequestBody @Valid CustomerPaymentDTO customerPaymentDTO) {
        log.info ("Received request to update customer payment {}", customerPaymentDTO);
        CustomerPaymentDTO updatedCustomerPayment = customerPaymentService.update (customerPaymentDTO);
        DataResult<CustomerPaymentDTO> response = new DataResult<> (
                updatedCustomerPayment,
                true, UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer payment {}", id);
        this.customerPaymentService.deleteById (id);
    }
}
