package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerPolicyRenewal;
import intern.customer.agitoo.Service.Abstracts.ICustomerPolicyRenewalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerpolicyrenewal")
public class CustomerPolicyRenewalController {

    @Autowired
    private ICustomerPolicyRenewalService customerPolicyRenewalsService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerPolicyRenewal>> getAll () {
        return this.customerPolicyRenewalsService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerPolicyRenewal customerPolicyRenewal) {
        return this.customerPolicyRenewalsService.Add (customerPolicyRenewal);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerPolicyRenewal customerPolicyRenewal) {
        return this.customerPolicyRenewalsService.Update (customerPolicyRenewal);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerPolicyRenewalsService.Delete (id);
    }
}
