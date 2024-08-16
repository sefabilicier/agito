package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Service.Abstracts.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @GetMapping(value = "/getall")
    public DataResult<List<Customer>> getAll () {
        return this.customerService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (Customer customer) {
        return this.customerService.Add (customer);

    }

    @PutMapping(value = "/update")
    public Result Update (Customer customer) {
        return this.customerService.Update (customer);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerService.Delete (id);
    }
}

