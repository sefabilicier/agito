package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
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
@RequestMapping("/api/customer-claim")
public class CustomerClaimController {

    @Autowired
    private ICustomerClaimService customerClaimsService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerClaimDTO>>>> getAll () {
        log.info ("Received request to list customer claims!");
        return customerClaimsService.getAll ().thenApply (
                customerClaimList -> {
                    DataResult<List<CustomerClaimDTO>> response = new DataResult<> (
                            customerClaimList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerClaimDTO>>> Add (@RequestBody @Valid CustomerClaimDTO customerClaimDTO) {
        log.info ("Received request to add customer claim {}", customerClaimDTO);
        return customerClaimsService.add (customerClaimDTO).thenApply (
                customerClaim -> {
                    DataResult<CustomerClaimDTO> response = new DataResult<> (
                            customerClaim, true, ADDED
                    );

                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerClaimDTO>>> Update (@RequestBody @Valid CustomerClaimDTO customerClaimDTO) {
        log.info ("Received request to update customer claim {}", customerClaimDTO);
        return customerClaimsService.update (customerClaimDTO).thenApply (
                customerClaim -> {
                    DataResult<CustomerClaimDTO> response = new DataResult<> (
                            customerClaim,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer claim {}", id);
        return customerClaimsService.deleteById (id)
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
