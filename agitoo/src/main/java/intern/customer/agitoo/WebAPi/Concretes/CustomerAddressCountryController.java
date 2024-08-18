package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
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
@RequestMapping("/api/customer-address-country")
public class CustomerAddressCountryController {

    @Autowired
    private ICustomerAddressCountryService customerAddressCountryService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerAddressCountryDTO>>> getAll () {
        log.info("Received request to list customer address countries!");
        List<CustomerAddressCountryDTO> customerAddressCityDTOList = customerAddressCountryService.getAll ();
        DataResult<List<CustomerAddressCountryDTO>> response = new DataResult<> (
                customerAddressCityDTOList,
                true,
                "Customer address countries listed! "
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerAddressCountryDTO>> Add (CustomerAddressCountryDTO customerAddressCountryDTO) {
        log.info("Received request to add customer address country {}", customerAddressCountryDTO);
        CustomerAddressCountryDTO customerAddressCountry = customerAddressCountryService.add (customerAddressCountryDTO);
        DataResult<CustomerAddressCountryDTO> response = new DataResult<> (customerAddressCountry, true, "Custoemer address country added! ");
        return ResponseEntity.ok (response);
    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerAddressCountryDTO>> Update (CustomerAddressCountryDTO customerAddressCountryDTO) {
        log.info("Received request to update customer address country {}", customerAddressCountryDTO);
        CustomerAddressCountryDTO customerAddressCountry = customerAddressCountryService.update (customerAddressCountryDTO);
        DataResult<CustomerAddressCountryDTO> response = new DataResult<> (
                customerAddressCountry, true, "Customer address country updated!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete customer address country {}", id);
        this.customerAddressCountryService.deleteById (id);
    }
}
