package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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
@RequestMapping("/api/customerpolicy")
public class CustomerPolicyController {

    @Autowired
    private ICustomerPolicyService customerPolicyService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerPolicyDTO>>> getAll () {
        log.info("Received request to list customer policies!");
        List<CustomerPolicyDTO> customerPolicyDTOList = customerPolicyService.getAll ();
        DataResult<List<CustomerPolicyDTO>> response = new DataResult<> (
                customerPolicyDTOList,
                true,
                "Customer policies listed"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerPolicyDTO>> Add (CustomerPolicyDTO customerPolicyDTO) {
        log.info("Received request to add customer policy {}", customerPolicyDTO);
        CustomerPolicyDTO savedCustomerPolicyDTO = customerPolicyService.add (customerPolicyDTO);
        DataResult<CustomerPolicyDTO> response = new DataResult<> (
                savedCustomerPolicyDTO, true, "Customer policy added!"
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerPolicyDTO>> Update (CustomerPolicyDTO customerPolicyDTO) {
        log.info("Received request to update customer policy {}", customerPolicyDTO);
        CustomerPolicyDTO updatedCustomerPolicyDTO = customerPolicyService.update (customerPolicyDTO);
        DataResult<CustomerPolicyDTO> response = new DataResult<> (updatedCustomerPolicyDTO, true,
                "Customer policy updated!");
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer policy {}", id);
        this.customerPolicyService.deleteById (id);
    }
}
