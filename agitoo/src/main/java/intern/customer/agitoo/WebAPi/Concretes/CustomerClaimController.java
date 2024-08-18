package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
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
@RequestMapping("/api/customer-claim")
public class CustomerClaimController {

    @Autowired
    private ICustomerClaimService customerClaimsService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerClaimDTO>>> getAll () {
        log.info("Received request to list customer claims!");
        List<CustomerClaimDTO> customerClaimList = customerClaimsService.getAll ();
        DataResult<List<CustomerClaimDTO>> response = new DataResult<> (
                customerClaimList,
                true,
                "Customer claims listed!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerClaimDTO>> Add (CustomerClaimDTO customerClaimDTO) {
        log.info("Received request to add customer claim {}", customerClaimDTO);
        CustomerClaimDTO customerClaim = customerClaimsService.add (customerClaimDTO);
        DataResult<CustomerClaimDTO> response = new DataResult<> (
                customerClaim, true, "Customer claim added!"
        );

        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerClaimDTO>> Update (CustomerClaimDTO customerClaimDTO) {
        log.info("Received request to update customer claim {}", customerClaimDTO);
        CustomerClaimDTO customerClaim = customerClaimsService.update (customerClaimDTO);
        DataResult<CustomerClaimDTO> response = new DataResult<> (
                customerClaim,
                true, "Customer claim updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer claim {}", id);
        customerClaimsService.deleteById (id);
    }
}
