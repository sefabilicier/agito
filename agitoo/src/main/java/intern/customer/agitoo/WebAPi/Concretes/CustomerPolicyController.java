package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyService;
import jakarta.validation.Valid;
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
@RequestMapping("/api/customer-policy")
public class CustomerPolicyController {

    @Autowired
    private ICustomerPolicyService customerPolicyService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerPolicyDTO>>>> getAll () {
        log.info ("Received request to list customer policies!");
        return customerPolicyService.getAll ().thenApply (
                customerPolicyDTOList -> {
                    DataResult<List<CustomerPolicyDTO>> response = new DataResult<> (
                            customerPolicyDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerPolicyDTO>>> Add (@RequestBody @Valid CustomerPolicyDTO customerPolicyDTO) {
        log.info ("Received request to add customer policy {}", customerPolicyDTO);
        return customerPolicyService.add (customerPolicyDTO).thenApply (
                savedCustomerPolicyDTO -> {
                    DataResult<CustomerPolicyDTO> response = new DataResult<> (
                            savedCustomerPolicyDTO, true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerPolicyDTO>>> Update (@RequestBody @Valid CustomerPolicyDTO customerPolicyDTO) {
        log.info ("Received request to update customer policy {}", customerPolicyDTO);
        return customerPolicyService.update (customerPolicyDTO).thenApply (
                updatedCustomerPolicyDTO -> {
                    DataResult<CustomerPolicyDTO> response = new DataResult<> (updatedCustomerPolicyDTO, true,
                            UPDATED);
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable Long id) {
        log.info ("Received request to delete customer policy {}", id);
        return customerPolicyService.deleteById (id)
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
