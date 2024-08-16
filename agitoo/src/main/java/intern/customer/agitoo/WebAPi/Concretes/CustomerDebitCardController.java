package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerDebitCard;
import intern.customer.agitoo.Service.Abstracts.ICustomerDebitCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerdebitcard")
public class CustomerDebitCardController {

    @Autowired
    private ICustomerDebitCardService customerDebitCardsService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerDebitCard>> getAll () {
        return this.customerDebitCardsService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerDebitCard customerDebitCard) {
        return this.customerDebitCardsService.Add (customerDebitCard);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerDebitCard customerDebitCard) {
        return this.customerDebitCardsService.Update (customerDebitCard);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerDebitCardsService.Delete (id);
    }
}
