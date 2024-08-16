package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/companybranch")
public class CompanyBranchController {

    @Autowired
    private ICompanyBranchService companyBranchService;

    @GetMapping(value = "/getall")
    public DataResult<List<CompanyBranch>> getAll () {

        return companyBranchService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (@RequestBody CompanyBranch companyBranch) {
        return companyBranchService.Add (companyBranch);

    }

    @PutMapping(value = "/update")
    public Result Update (@RequestBody CompanyBranch companyBranch) {
        return companyBranchService.Update (companyBranch);
    }

    @DeleteMapping("/{id}")
    public Result Delete (@PathVariable Long id) {
        return companyBranchService.Delete (id);
    }
}