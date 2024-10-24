package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Service.Concretes.CompanyBranchServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
//@Controller and @ResponseBody both in one --> @RestController: Combines @Controller and @ResponseBody, which means it returns data (like JSON) directly, bypassing view resolution.
@RequestMapping("/ui/company-branch/")
public class CompanyBranchUIController {

    @Autowired
    private CompanyBranchServiceImpl companyBranchService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list company branches!");
        List<CompanyBranchDTO> companyBranchDTOList = companyBranchService.getAll ().join ();
        model.addAttribute ("branches", companyBranchDTOList);
        return "company_branch/company_branch_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompanyBranch (@RequestParam(value = "companyId", required = false) Long companyId, Model model, CompanyBranchDTO companyBranchDTO) {
        log.info ("HTML - Received request to CREATE company branch {}", companyBranchDTO);
        model.addAttribute ("companyBranchDTO", companyBranchDTO);

//        model.addAttribute ("companyId", companyBranchDTO.getCompany ().getCompanyId ()); //TODO adding to HTML
//        Company company = Company.builder().companyId (companyId).build ();
//        companyBranchDTO.setCompany (company);

        companyBranchDTO.setCompanyId (companyId);

        return "company_branch/company_branch_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCompanyBranch (@ModelAttribute("companyBranchDTO") @Valid CompanyBranchDTO companyBranchDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", companyBranchDTO);

        Company company = Company.builder ()
                .companyId (companyBranchDTO.getCompanyId ())
                .build ();
        companyBranchDTO.setCompany (company);

        CompanyBranchDTO savedCompanyBranch = companyBranchService.add (companyBranchDTO).join ();

//        Long createdCompanyBranchId = (Long) httpSession.getAttribute ("selectedCompanyId");
//        Company company = Company.builder ().companyId (createdCompanyBranchId).build ();
//        companyBranchDTO.setCompany (company);

        return "redirect:/ui/company-financial/add?companyId=" + savedCompanyBranch.getCompanyId ();
    }

    @RequestMapping(value = "/update/{branchID}/edit", method = RequestMethod.GET)
    public String editCompanyBranch (@PathVariable("branchID") Long branchID, Model model) {
        CompanyBranchDTO branchFind = companyBranchService.findById (branchID).join ();
        log.info ("HTML - Received request to EDIT company branch with ID {}", branchID);
        model.addAttribute ("companyBranchDTO", branchFind);
        return "company_branch/company_branch_update";

    }

    @RequestMapping(value = "/update/{branchID}/edit", method = RequestMethod.POST)
    public String updateCompanyBranch (@PathVariable("branchID") Long branchID,
                                       @ModelAttribute("personDTO") @Valid CompanyBranchDTO companyBranchDTO,
                                       Model model) {
        log.info ("HTML - Received request to UPDATE company branch  {}", companyBranchDTO);
        companyBranchDTO.setBranchID (branchID);
        CompanyBranchDTO updatedBranch = companyBranchService.update (companyBranchDTO).join ();
        model.addAttribute ("companyBranchDTO", updatedBranch);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personDTO") CompanyBranchDTO companyBranchDTO, Model model) {
        log.info ("HTML - Confirming update for company branch {}", companyBranchDTO);
        CompanyBranchDTO updatedBranch = companyBranchService.update (companyBranchDTO).join ();
        model.addAttribute ("companyBranchDTO", updatedBranch);
        return "redirect:/ui/company-branch/get-all";
    }

}
