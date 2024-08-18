package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
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
@RequestMapping("/api/customer-address-city")
public class CustomerAddressCityController {

    @Autowired
    private ICustomerAddressCityService customerAddressCityService;


    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerAddressCityDTO>>> getAll () {
        log.info("Received request to list customer address cities!");
        List<CustomerAddressCityDTO> customerAddressCityDTOList = customerAddressCityService.getAll ();
        DataResult<List<CustomerAddressCityDTO>> response = new DataResult<> (
                customerAddressCityDTOList,
                true,
                "Customer Address cities listed!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerAddressCityDTO>> Add (CustomerAddressCityDTO customerAddressCityDTO) {
        log.info("Received request to add customer address city {}", customerAddressCityDTO);
        CustomerAddressCityDTO savedCustomerAddressCity =  customerAddressCityService.add (customerAddressCityDTO);
        DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                savedCustomerAddressCity, true, "Customer address city added!");
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerAddressCityDTO>> Update (CustomerAddressCityDTO customerAddressCityDTO) {
        log.info("Received request to update customer address city {}", customerAddressCityDTO);
        CustomerAddressCityDTO updaCustomerAddressCity =  customerAddressCityService.update (customerAddressCityDTO);
        DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                updaCustomerAddressCity,
                true, "Company address city updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer address city {}", id);
        this.customerAddressCityService.deleteById (id);
    }
}
