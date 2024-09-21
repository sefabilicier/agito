package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.Service.Concretes.CompanyFinancialServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/company-financial/")
public class CompanyFinancialUIController {

    @Autowired
    private CompanyFinancialServiceImpl companyFinancialService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model)
    {
        log.info ("HTML - Received request to list company financials!");
        List<CompanyFinancialDTO> companyFinancialDTOList = companyFinancialService.getAll ();
        model.addAttribute ("financials", companyFinancialDTOList);
        return "company_financial/company_financials_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CompanyFinancialDTO companyFinancialDTO){
        log.info ("HTML - Received request to CREATE company {}", companyFinancialDTO);
        model.addAttribute ("companyFinancialDTO", companyFinancialDTO);
        return "company_financial/company_financial_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("companyFinancialDTO") CompanyFinancialDTO companyFinancialDTO){
        log.info ("HTML - SAVED customer {}", companyFinancialDTO);
        companyFinancialService.add (companyFinancialDTO);

        return "redirect:/ui/company-branch/add";
    }

}
