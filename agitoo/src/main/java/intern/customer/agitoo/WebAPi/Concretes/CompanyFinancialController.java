package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Service.Abstracts.ICompanyFinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/companyfinancial")
public class CompanyFinancialController {

    @Autowired
    private ICompanyFinancialService companyFinancialService;


    @GetMapping(value = "/getall")
    public DataResult<List<CompanyFinancial>> getAll () {
        return this.companyFinancialService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (CompanyFinancial companyFinancials) {
        return this.companyFinancialService.Add (companyFinancials);

    }

    @PutMapping(value = "/update")
    public Result Update (CompanyFinancial companyFinancials) {
        return this.companyFinancialService.Update (companyFinancials);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.companyFinancialService.Delete (id);
    }
}
