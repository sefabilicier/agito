package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/customer-address-city")
public class CustomerAddressCityController {

    @Autowired
    private ICustomerAddressCityService customerAddressCityService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerAddressCityDTO>>> getAll () {
        log.info ("Received request to list customer address cities!");
        List<CustomerAddressCityDTO> customerAddressCityDTOList = customerAddressCityService.getAll ();
        DataResult<List<CustomerAddressCityDTO>> response = new DataResult<> (
                customerAddressCityDTOList,
                true,
                LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerAddressCityDTO>> Add (@RequestBody @Valid CustomerAddressCityDTO customerAddressCityDTO) {
        log.info ("Received request to add customer address city {}", customerAddressCityDTO);
        CustomerAddressCityDTO savedCustomerAddressCity = customerAddressCityService.add (customerAddressCityDTO);
        DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                savedCustomerAddressCity, true, ADDED);
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerAddressCityDTO>> Update (@RequestBody @Valid CustomerAddressCityDTO customerAddressCityDTO) {
        log.info ("Received request to update customer address city {}", customerAddressCityDTO);
        CustomerAddressCityDTO updaCustomerAddressCity = customerAddressCityService.update (customerAddressCityDTO);
        DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                updaCustomerAddressCity,
                true, UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer address city {}", id);
        this.customerAddressCityService.deleteById (id);
    }
}
