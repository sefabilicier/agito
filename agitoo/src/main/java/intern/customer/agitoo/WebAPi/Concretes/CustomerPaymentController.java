package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
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
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/customer-payment")
public class CustomerPaymentController {

    @Autowired
    private ICustomerPaymentService customerPaymentService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerPaymentDTO>>>> getAll () {
        log.info ("Received request to list customer payments!");
        return customerPaymentService.getAll ().thenApply (
                customerPaymentDTOList -> {
                    DataResult<List<CustomerPaymentDTO>> response = new DataResult<> (
                            customerPaymentDTOList,
                            true,
                            LISTED
                    );

                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerPaymentDTO>>> Add (@RequestBody @Valid CustomerPaymentDTO customerPaymentDTO) {
        log.info ("Received request to add customer payment {}", customerPaymentDTO);
        return customerPaymentService.add (customerPaymentDTO).thenApply (
                savedCustomerPayment -> {
                    DataResult<CustomerPaymentDTO> response = new DataResult<> (
                            savedCustomerPayment, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerPaymentDTO>>> Update (@RequestBody @Valid CustomerPaymentDTO customerPaymentDTO) {
        log.info ("Received request to update customer payment {}", customerPaymentDTO);
        return customerPaymentService.update (customerPaymentDTO).thenApply (
                updatedCustomerPayment -> {
                    DataResult<CustomerPaymentDTO> response = new DataResult<> (
                            updatedCustomerPayment,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer payment {}", id);
        return customerPaymentService.deleteById (id)
                .thenApply (result -> {
                    DataResult<Void> response = new DataResult<> (
                            result,
                            true,
                            REMOVED
                    );
                    return ResponseEntity.ok (response);
                });
    }
}
