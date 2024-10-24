package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
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
@RequestMapping("/api/customer-policy-renewal")
public class CustomerPolicyRenewalController {

    @Autowired
    private ICustomerPolicyRenewalService customerPolicyRenewalsService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerPolicyRenewalDTO>>>> getAll () {
        log.info ("Received request to list customer policy renewals!");
        return customerPolicyRenewalsService.getAll ().thenApply (
                customerPolicyRenewalDTOList -> {
                    DataResult<List<CustomerPolicyRenewalDTO>> response = new DataResult<> (
                            customerPolicyRenewalDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerPolicyRenewalDTO>>> Add (@RequestBody CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info ("Received request to add customer policy renewal {}", customerPolicyRenewalDTO);
        return customerPolicyRenewalsService
                .add (customerPolicyRenewalDTO).thenApply (
                        savedCustomerPolicyRenewalDTO -> {
                            DataResult<CustomerPolicyRenewalDTO> response = new DataResult<> (
                                    savedCustomerPolicyRenewalDTO, true, ADDED
                            );
                            return ResponseEntity.ok (response);
                        }
                );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerPolicyRenewalDTO>>> Update (@RequestBody @Valid CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info ("Received request to update customer policy renewal {}", customerPolicyRenewalDTO);
        return customerPolicyRenewalsService.update (customerPolicyRenewalDTO).thenApply (
                updatedCustomerPolicyRenewalDTO -> {
                    DataResult<CustomerPolicyRenewalDTO> response = new DataResult<> (
                            updatedCustomerPolicyRenewalDTO,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer policy renewal {}", id);
        return customerPolicyRenewalsService.deleteById (id)
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
