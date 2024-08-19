package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
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
@Controller
@RestController
@RequestMapping("/api/customer-address")
public class CustomerAddressController {

    @Autowired
    private ICustomerAddressService customerAddressService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerAddressDTO>>> getAll () {
        log.info("Received request to list customer addresses!");
        List<CustomerAddressDTO> customerAddressDTOList =  customerAddressService.getAll ();
        DataResult<List<CustomerAddressDTO>> response = new DataResult<> (
                customerAddressDTOList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerAddressDTO>> Add (@RequestBody @Valid CustomerAddressDTO customerAddressDTO) {
        log.info("Received request to add customer addresses {}", customerAddressDTO);
        CustomerAddressDTO customerAddress =  customerAddressService.add (customerAddressDTO);
        DataResult<CustomerAddressDTO> response = new DataResult<> (
                customerAddress,
                true, Messages.ADDED
        );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerAddressDTO>> Update (@RequestBody @Valid CustomerAddressDTO customerAddressDTO) {
        log.info("Received request to update customer addresses {}", customerAddressDTO);
        CustomerAddressDTO updatedCustomerAddress = customerAddressService.update (customerAddressDTO);
        DataResult<CustomerAddressDTO> response = new DataResult<> (updatedCustomerAddress, true, Messages.UPDATED);
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer addresses {}", id);
        this.customerAddressService.deleteById (id);
    }
}
