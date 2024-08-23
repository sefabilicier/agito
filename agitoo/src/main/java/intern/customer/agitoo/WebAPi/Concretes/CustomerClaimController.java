package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
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
@RestController
@RequestMapping("/api/customer-claim")
public class CustomerClaimController {

    @Autowired
    private ICustomerClaimService customerClaimsService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerClaimDTO>>> getAll () {
        log.info("Received request to list customer claims!");
        List<CustomerClaimDTO> customerClaimList = customerClaimsService.getAll ();
        DataResult<List<CustomerClaimDTO>> response = new DataResult<> (
                customerClaimList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerClaimDTO>> Add (@RequestBody @Valid CustomerClaimDTO customerClaimDTO) {
        log.info("Received request to add customer claim {}", customerClaimDTO);
        CustomerClaimDTO customerClaim = customerClaimsService.add (customerClaimDTO);
        DataResult<CustomerClaimDTO> response = new DataResult<> (
                customerClaim, true, Messages.ADDED
        );

        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerClaimDTO>> Update (@RequestBody @Valid CustomerClaimDTO customerClaimDTO) {
        log.info("Received request to update customer claim {}", customerClaimDTO);
        CustomerClaimDTO customerClaim = customerClaimsService.update (customerClaimDTO);
        DataResult<CustomerClaimDTO> response = new DataResult<> (
                customerClaim,
                true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer claim {}", id);
        customerClaimsService.deleteById (id);
    }
}
