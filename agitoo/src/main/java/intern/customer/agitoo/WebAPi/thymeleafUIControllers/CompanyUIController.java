package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.DTO.Mappers.CustomerMapper;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Service.Concretes.CompanyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import static intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules.customerTypeRedirection.selectedCustomerType;

@Slf4j
@Controller
@RequestMapping("/ui/company/")
public class CompanyUIController {

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private CompanyMapper companyMapper;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list companies!");
        List<CompanyDTO> companyDTO = companyService.getAll ();
        model.addAttribute ("companies", companyDTO);
        return "company/company_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CompanyDTO companyDTO){
        log.info ("HTML - Received request to CREATE company {}", companyDTO);
        model.addAttribute ("companyDTO", companyDTO);
        return "company/company_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("companyDTO") CompanyDTO companyDTO){
        log.info ("HTML - SAVED customer {}", companyDTO);
        companyService.add (companyDTO);

        return "redirect:ui/company-financial/add";
    }
}
