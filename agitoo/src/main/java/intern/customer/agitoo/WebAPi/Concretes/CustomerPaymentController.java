package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
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
@RequestMapping("/api/customerpayment")
public class CustomerPaymentController {

    @Autowired
    private ICustomerPaymentService customerPaymentService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerPaymentDTO>>> getAll () {
        log.info("Received request to list customer payments!");
        List<CustomerPaymentDTO> customerPaymentDTOList = customerPaymentService.getAll ();
        DataResult<List<CustomerPaymentDTO>> response = new DataResult<> (
                customerPaymentDTOList,
                true,
                "Customer payments listed! "
        );

        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerPaymentDTO>> Add (CustomerPaymentDTO customerPaymentDTO) {
        log.info("Received request to add customer payment {}", customerPaymentDTO);
        CustomerPaymentDTO savedCustomerPayment = customerPaymentService.add (customerPaymentDTO);
        DataResult<CustomerPaymentDTO> response = new DataResult<> (
                savedCustomerPayment, true, "Customer payment added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerPaymentDTO>> Update (CustomerPaymentDTO customerPaymentDTO) {
        log.info("Received request to update customer payment {}", customerPaymentDTO);
        CustomerPaymentDTO updatedCustomerPayment = customerPaymentService.update (customerPaymentDTO);
        DataResult<CustomerPaymentDTO> response = new DataResult<> (
                updatedCustomerPayment,
                true, "Customer payment updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer payment {}", id);
        this.customerPaymentService.deleteById (id);
    }
}
