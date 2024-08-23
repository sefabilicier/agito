package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICustomerDebitCardService;
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
@RestController
@RequestMapping("/api/customer-debit-card")
public class CustomerDebitCardController {

    @Autowired
    private ICustomerDebitCardService customerDebitCardsService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CustomerDebitCardDTO>>> getAll () {
        log.info("Received request to list customer debit cards!");
        List<CustomerDebitCardDTO> customerDebitCardList = customerDebitCardsService.getAll ();
        DataResult<List<CustomerDebitCardDTO>> response = new DataResult<> (
                customerDebitCardList,
                true,
                Messages.LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CustomerDebitCardDTO>> Add (@RequestBody @Valid CustomerDebitCardDTO customerDebitCardDTO) {
        log.info("Received request to add customer debit card {}", customerDebitCardDTO);
        CustomerDebitCardDTO savedCustomerDebitCardDTO = customerDebitCardsService.add (customerDebitCardDTO);
        DataResult<CustomerDebitCardDTO> response = new DataResult<>(
                savedCustomerDebitCardDTO,
                true,
                Messages.ADDED
                );
        return ResponseEntity.ok (response);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CustomerDebitCardDTO>> Update (@RequestBody @Valid CustomerDebitCardDTO customerDebitCardDTO) {
        log.info("Received request to update customer debit card {}", customerDebitCardDTO);
        CustomerDebitCardDTO updateCustomerDebitCardDTO = customerDebitCardsService.update (customerDebitCardDTO);
        DataResult<CustomerDebitCardDTO> response = new DataResult<> (
                updateCustomerDebitCardDTO, true, Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete customer debit card {}", id);
        this.customerDebitCardsService.deleteById (id);
    }
}
