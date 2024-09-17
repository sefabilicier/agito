package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.Service.Concretes.CompanyBranchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller //@Controller and @ResponseBody both in one --> @RestController: Combines @Controller and @ResponseBody, which means it returns data (like JSON) directly, bypassing view resolution.
@RequestMapping("/ui/company-branch/")
public class CompanyBranchUIController {

    @Autowired
    private CompanyBranchServiceImpl companyBranchService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list company branches!");
        List<CompanyBranchDTO> companyBranchDTOList = companyBranchService.getAll ();
        model.addAttribute ("branches", companyBranchDTOList);
        return "company_branch/company_branch_get_all";
    }

}
