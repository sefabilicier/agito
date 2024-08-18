package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
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
@RequestMapping("/api/customerpolicyrenewal")
public class CustomerPolicyRenewalController {

    @Autowired
    private ICustomerPolicyRenewalService customerPolicyRenewalsService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerPolicyRenewalDTO>>> getAll () {
        log.info("Received request to list customer policy renewals!");
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOList = customerPolicyRenewalsService.getAll ();
        DataResult<List<CustomerPolicyRenewalDTO>> response = new DataResult<> (
                customerPolicyRenewalDTOList,
                true,
                "Customer policy renewals listed! "
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerPolicyRenewalDTO>> Add (CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info("Received request to add customer policy renewal {}", customerPolicyRenewalDTO);
        CustomerPolicyRenewalDTO savedCustomerPolicyRenewalDTO = customerPolicyRenewalsService
                .add (customerPolicyRenewalDTO);
        DataResult<CustomerPolicyRenewalDTO> response = new DataResult<> (
                savedCustomerPolicyRenewalDTO, true, "Customer policy renewal saved"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerPolicyRenewalDTO>> Update (CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info("Received request to update customer policy renewal {}", customerPolicyRenewalDTO);
        CustomerPolicyRenewalDTO updatedCustomerPolicyRenewalDTO = customerPolicyRenewalsService.update (customerPolicyRenewalDTO);
        DataResult<CustomerPolicyRenewalDTO> response = new DataResult<>(
                updatedCustomerPolicyRenewalDTO,
                true, "Customer policy renewal updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer policy renewal {}", id);
        this.customerPolicyRenewalsService.deleteById (id);
    }
}
