package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Service.Concretes.CompanyFinancialServiceImpl;
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
@RequestMapping("/ui/company-financial/")
public class CompanyFinancialUIController {

    @Autowired
    private CompanyFinancialServiceImpl companyFinancialService;
    @Autowired
    private HttpSession httpSession;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list company financials!");
        List<CompanyFinancialDTO> companyFinancialDTOList = companyFinancialService.getAll ().join ();
        model.addAttribute ("financials", companyFinancialDTOList);
        return "company_financial/company_financials_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompanyFinancial (@RequestParam(value = "companyId", required = false) Long companyId, Model model, CompanyFinancialDTO companyFinancialDTO) {
        log.info ("HTML - Received request to CREATE company {}", companyFinancialDTO);
        model.addAttribute ("companyFinancialDTO", companyFinancialDTO);

//        model.addAttribute ("companyId", companyFinancialDTO.getCompanies ().getCompanyId ()); //TODO adding to HTML
//        Company company = Company.builder().companyId (companyId).build ();
//        companyFinancialDTO.setCompanies (company);

        companyFinancialDTO.setCompanyId (companyId);

        return "company_financial/company_financial_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCompanyFinancial (@ModelAttribute("companyFinancialDTO") @Valid CompanyFinancialDTO companyFinancialDTO) {
        log.info ("HTML - SAVED customer {}", companyFinancialDTO);

        Company company = Company.builder ()
                .companyId (companyFinancialDTO.getCompanyId ())
                .build ();
        companyFinancialDTO.setCompanies (company);

        CompanyFinancialDTO savedCompanyFinancial = companyFinancialService.add (companyFinancialDTO).join ();

//        Long createdCompanyFinancialId = (Long) httpSession.getAttribute ("selectedCompanyId");
//        Company company = Company.builder ().companyId (createdCompanyFinancialId).build ();
//        companyFinancialDTO.setCompany (company);

        return "redirect:/ui/customer-contact/add?customerId=" + savedCompanyFinancial.getCompanies ().getCustomer ().getCustomerId ();
    }

    @RequestMapping(value = "/update/{financialID}/edit", method = RequestMethod.GET)
    public String editCompanyFinancial (@PathVariable("financialID") Long financialID, Model model) {
        CompanyFinancialDTO financialFind = companyFinancialService.findById (financialID).join ();
        log.info ("HTML - Received request to EDIT company financial with ID {}", financialID);
        model.addAttribute ("companyFinancialDTO", financialFind);
        return "company_financial/company_financial_update";

    }


    @RequestMapping(value = "/update/{financialID}/edit", method = RequestMethod.POST)
    public String updateCompanyFinancial (@PathVariable("financialID") Long financialID, @ModelAttribute("companyFinancialDTO") @Valid CompanyFinancialDTO companyFinancialDTO, Model model) {
        log.info ("HTML - Received request to UPDATE company financial {}", companyFinancialDTO);

        companyFinancialDTO.setFinancialID (financialID);
        CompanyFinancialDTO updatedFinancial = companyFinancialService.update (companyFinancialDTO).join ();
        model.addAttribute ("companyFinancialDTO", updatedFinancial);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("companyFinancialDTO") CompanyFinancialDTO companyFinancialDTO, Model model) {
        log.info ("HTML - Confirming update for companyFinancial {}", companyFinancialDTO);
        CompanyFinancialDTO updatedFinancial = companyFinancialService.update (companyFinancialDTO).join ();
        model.addAttribute ("companyFinancialDTO", updatedFinancial);
        return "redirect:/ui/company-financial/get-all";
    }

}
