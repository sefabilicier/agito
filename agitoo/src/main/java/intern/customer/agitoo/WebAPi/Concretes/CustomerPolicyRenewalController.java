package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.Helper.Messages;
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

@Slf4j
@RestController
@RequestMapping("/api/customer-policy-renewal")
public class CustomerPolicyRenewalController {

    @Autowired
    private ICustomerPolicyRenewalService customerPolicyRenewalsService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerPolicyRenewalDTO>>> getAll () {
        log.info("Received request to list customer policy renewals!");
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOList = customerPolicyRenewalsService.getAll ();
        DataResult<List<CustomerPolicyRenewalDTO>> response = new DataResult<> (
                customerPolicyRenewalDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerPolicyRenewalDTO>> Add (@RequestBody CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info("Received request to add customer policy renewal {}", customerPolicyRenewalDTO);
        CustomerPolicyRenewalDTO savedCustomerPolicyRenewalDTO = customerPolicyRenewalsService
                .add (customerPolicyRenewalDTO);
        DataResult<CustomerPolicyRenewalDTO> response = new DataResult<> (
                savedCustomerPolicyRenewalDTO, true, Messages.ADDED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerPolicyRenewalDTO>> Update (@RequestBody @Valid CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info("Received request to update customer policy renewal {}", customerPolicyRenewalDTO);
        CustomerPolicyRenewalDTO updatedCustomerPolicyRenewalDTO = customerPolicyRenewalsService.update (customerPolicyRenewalDTO);
        DataResult<CustomerPolicyRenewalDTO> response = new DataResult<>(
                updatedCustomerPolicyRenewalDTO,
                true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer policy renewal {}", id);
        this.customerPolicyRenewalsService.deleteById (id);
    }
}
