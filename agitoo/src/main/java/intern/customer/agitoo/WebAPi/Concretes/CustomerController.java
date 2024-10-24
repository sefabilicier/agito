package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
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
@RestController //@Controller and @ResponseBody both in one
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerDTO>>>> getAll () {
        log.info ("Received request to list customers!");
        return customerService.getAll ()
                .thenApply (
                        customerDTOList -> {
                            DataResult<List<CustomerDTO>> response = new DataResult<> (
                                    customerDTOList, true, LISTED);
                            return ResponseEntity.ok (response);
                        });
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerDTO>>> Add (@RequestBody @Valid CustomerDTO customerDTO) {
        log.info ("Received request to add customer {}", customerDTO);
        return customerService.add (customerDTO)
                .thenApply (
                        savedCustomerDTO -> {
                            DataResult<CustomerDTO> response = new DataResult<> (
                                    savedCustomerDTO, true, ADDED);
                            return ResponseEntity.ok (response);
                        });
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerDTO>>> Update (@RequestBody @Valid CustomerDTO customerDTO) {
        log.info ("Received request to update customer {}", customerDTO);

        return customerService.update (customerDTO)
                .thenApply (
                        updatedCustomer -> {
                            DataResult<CustomerDTO> response = new DataResult<> (
                                    updatedCustomer, true, UPDATED);
                            return ResponseEntity.ok (response);
                        });
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer {}", id);
        return customerService.deleteById (id)
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