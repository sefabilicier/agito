package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerContact;
import intern.customer.agitoo.Service.Abstracts.ICustomerContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customercontact")
public class CustomerContactController {

    @Autowired
    private ICustomerContactService customerContactService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerContact>> getAll () {
        return this.customerContactService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerContact customerContact) {
        return this.customerContactService.Add (customerContact);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerContact customerContact) {
        return this.customerContactService.Update (customerContact);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerContactService.Delete (id);
    }
}
