package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/companies")
//@Tag(name = "Company API", description = "Operations related to companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;


    @GetMapping(value = "/getall")
    //@Operation(summary = "Get all company")
    public DataResult<List<Company>> getAll () {
        return companyService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (@RequestBody Company company) {
        return this.companyService.Add (company);
    }

    @PutMapping(value = "/update")
    public Result Update (@RequestBody Company company) {
        return this.companyService.Update (company);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.companyService.Delete (id);
    }
}