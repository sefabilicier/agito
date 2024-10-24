package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
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
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/customer-registration")
public class CustomerRegistrationController {

    @Autowired
    private ICustomerRegistrationService customerRegistrationService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerRegistrationDTO>>>> getAll () {
        log.info ("Received request to list customer registrations!");
        return customerRegistrationService.getAll ().thenApply (
                customerRegistrationDTOList -> {
                    DataResult<List<CustomerRegistrationDTO>> response = new DataResult<> (
                            customerRegistrationDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerRegistrationDTO>>> Add (@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO) {
        log.info ("Received request to add customer registration {}", customerRegistrationDTO);
        return customerRegistrationService.add (customerRegistrationDTO).thenApply (
                savedCustomerRegistrationDTO -> {
                    DataResult<CustomerRegistrationDTO> response = new DataResult<> (
                            savedCustomerRegistrationDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerRegistrationDTO>>> Update (@RequestBody @Valid CustomerRegistrationDTO customerRegistrationDTO) {
        log.info ("Received request to update customer registration {}", customerRegistrationDTO);
        return customerRegistrationService.update (customerRegistrationDTO).thenApply (
                updatedCustomerRegistrationDTO -> {
                    DataResult<CustomerRegistrationDTO> response = new DataResult<> (
                            updatedCustomerRegistrationDTO,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer registration {}", id);
        return customerRegistrationService.deleteById (id)
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
