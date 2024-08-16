package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerRegistration;
import intern.customer.agitoo.Service.Abstracts.ICustomerRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerregistration")
public class CustomerRegistrationController {

    @Autowired
    private ICustomerRegistrationService customerRegistrationService;

    @GetMapping(value = "/getall")
    public DataResult<List<CustomerRegistration>> getAll () {
        return this.customerRegistrationService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerRegistration customerRegistration) {
        return this.customerRegistrationService.Add (customerRegistration);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerRegistration customerRegistration) {
        return this.customerRegistrationService.Update (customerRegistration);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerRegistrationService.Delete (id);
    }
}
