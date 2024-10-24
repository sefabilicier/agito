package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
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
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/customer-address-city")
public class CustomerAddressCityController {

    @Autowired
    private ICustomerAddressCityService customerAddressCityService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerAddressCityDTO>>>> getAll () {
        log.info ("Received request to list customer address cities!");
        return customerAddressCityService.getAll ().thenApply (
                customerAddressCityDTOList -> {
                    DataResult<List<CustomerAddressCityDTO>> response = new DataResult<> (
                            customerAddressCityDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerAddressCityDTO>>> Add (@RequestBody @Valid CustomerAddressCityDTO customerAddressCityDTO) {
        log.info ("Received request to add customer address city {}", customerAddressCityDTO);
        return customerAddressCityService.add (customerAddressCityDTO).thenApply (
                savedCustomerAddressCity -> {
                    DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                            savedCustomerAddressCity, true, ADDED);
                    return ResponseEntity.ok (response);
                }
        );
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerAddressCityDTO>>> Update (@RequestBody @Valid CustomerAddressCityDTO customerAddressCityDTO) {
        log.info ("Received request to update customer address city {}", customerAddressCityDTO);
        return customerAddressCityService.update (customerAddressCityDTO).thenApply (
                updaCustomerAddressCity -> {
                    DataResult<CustomerAddressCityDTO> response = new DataResult<> (
                            updaCustomerAddressCity,
                            true, UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer address city {}", id);
        return customerAddressCityService.deleteById (id)
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
