package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerAddress;
import intern.customer.agitoo.Service.Abstracts.ICustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RestController
@RequestMapping("/api/customeraddress")
public class CustomerAddressController {

    @Autowired
    private ICustomerAddressService customerAddressService;

    @GetMapping(value = "/getall")
    public DataResult<List<CustomerAddress>> getAll () {
        return this.customerAddressService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerAddress customerAddress) {
        return this.customerAddressService.Add (customerAddress);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerAddress customerAddress) {
        return this.customerAddressService.Update (customerAddress);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerAddressService.Delete (id);
    }
}
