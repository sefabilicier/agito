package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressCityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customeraddresscity")
public class CustomerAddressCityController {

    @Autowired
    private ICustomerAddressCityService customerAddressCityService;


    @GetMapping(value = "/getall")
    public DataResult<List<CustomerAddressCity>> getAll () {
        return this.customerAddressCityService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerAddressCity customerAddressCity) {
        return this.customerAddressCityService.Add (customerAddressCity);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerAddressCity customerAddressCity) {
        return this.customerAddressCityService.Update (customerAddressCity);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerAddressCityService.Delete (id);
    }
}
