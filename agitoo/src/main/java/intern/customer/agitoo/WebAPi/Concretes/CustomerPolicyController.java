package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerpolicy")
public class CustomerPolicyController {

    @Autowired
    private ICustomerPolicyService customerPolicyService;

    @GetMapping(value = "/getall")
    public DataResult<List<CustomerPolicy>> getAll () {
        return this.customerPolicyService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerPolicy customerPolicy) {
        return this.customerPolicyService.Add (customerPolicy);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerPolicy customerPolicy) {
        return this.customerPolicyService.Update (customerPolicy);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerPolicyService.Delete (id);
    }
}
