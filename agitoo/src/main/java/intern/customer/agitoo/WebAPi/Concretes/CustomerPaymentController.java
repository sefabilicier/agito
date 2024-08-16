package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CustomerPayment;
import intern.customer.agitoo.Service.Abstracts.ICustomerPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/customerpayment")
public class CustomerPaymentController {

    @Autowired
    private ICustomerPaymentService customerPaymentService;

    @GetMapping(value = "/getall")
    public DataResult<List<CustomerPayment>> getAll () {
        return this.customerPaymentService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CustomerPayment customerPayment) {
        return this.customerPaymentService.Add (customerPayment);

    }

    @PutMapping(value = "/update")
    public Result Update (CustomerPayment customerPayment) {
        return this.customerPaymentService.Update (customerPayment);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.customerPaymentService.Delete (id);
    }
}
