package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.Service.Concretes.CustomerPolicyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-policy/")
public class CustomerPolicyUIController {

    @Autowired
    private CustomerPolicyServiceImpl customerPolicyService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list customer policies!");
        List<CustomerPolicyDTO> customerPolicyDTOList = customerPolicyService.getAll ();
        model.addAttribute ("policies", customerPolicyDTOList);
        return "customer_policy/customer_policy_get_all";
    }
}
