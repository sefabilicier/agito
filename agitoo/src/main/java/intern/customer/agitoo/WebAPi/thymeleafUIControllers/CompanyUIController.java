package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.Mappers.CompanyMapper;
import intern.customer.agitoo.DTO.Mappers.CustomerMapper;
import intern.customer.agitoo.Service.Concretes.CompanyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

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
}
