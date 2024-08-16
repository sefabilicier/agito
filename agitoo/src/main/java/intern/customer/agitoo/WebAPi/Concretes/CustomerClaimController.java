package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerClaim;
import intern.customer.agitoo.Service.Abstracts.ICustomerClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerclaim")
public class CustomerClaimController {

    @Autowired
    private ICustomerClaimService customerClaimsService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerClaim>> getAll () {
        return this.customerClaimsService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerClaim customerClaims) {
        return this.customerClaimsService.Add (customerClaims);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerClaim customerClaims) {
        return this.customerClaimsService.Update (customerClaims);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerClaimsService.Delete (id);
    }
}
