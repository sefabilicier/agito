package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
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
@RequestMapping("/api/customer-address")
public class CustomerAddressController {

    @Autowired
    private ICustomerAddressService customerAddressService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CustomerAddressDTO>>>> getAll () {
        log.info ("Received request to list customer addresses!");
        return customerAddressService.getAll ().thenApply (
                customerAddressDTOList -> {
                    DataResult<List<CustomerAddressDTO>> response = new DataResult<> (
                            customerAddressDTOList,
                            true,
                            LISTED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CustomerAddressDTO>>> Add (@RequestBody @Valid CustomerAddressDTO customerAddressDTO) {
        log.info ("Received request to add customer addresses {}", customerAddressDTO);
        return customerAddressService.add (customerAddressDTO).thenApply (
                customerAddress -> {
                    DataResult<CustomerAddressDTO> response = new DataResult<> (
                            customerAddress,
                            true, ADDED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CustomerAddressDTO>>> Update (@RequestBody @Valid CustomerAddressDTO customerAddressDTO) {
        log.info ("Received request to update customer addresses {}", customerAddressDTO);
        return customerAddressService.update (customerAddressDTO).thenApply (
                updatedCustomerAddress -> {
                    DataResult<CustomerAddressDTO> response = new DataResult<> (updatedCustomerAddress, true, UPDATED);
                    return ResponseEntity.ok (response);
                }
        );
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete customer addresses {}", id);
        return customerAddressService.deleteById (id)
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

