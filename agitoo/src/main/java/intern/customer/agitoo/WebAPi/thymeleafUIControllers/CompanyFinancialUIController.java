package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.Service.Concretes.CompanyFinancialServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
