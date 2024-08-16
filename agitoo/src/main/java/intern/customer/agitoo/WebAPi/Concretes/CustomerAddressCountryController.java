package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customeraddresscountry")
public class CustomerAddressCountryController {

    @Autowired
    private ICustomerAddressCountryService customerAddressCountryService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerAddressCountry>> getAll () {
        return this.customerAddressCountryService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerAddressCountry customerAddressCountry) {
        return this.customerAddressCountryService.Add (customerAddressCountry);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerAddressCountry customerAddressCountry) {
        return this.customerAddressCountryService.Update (customerAddressCountry);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerAddressCountryService.Delete (id);
    }
}
