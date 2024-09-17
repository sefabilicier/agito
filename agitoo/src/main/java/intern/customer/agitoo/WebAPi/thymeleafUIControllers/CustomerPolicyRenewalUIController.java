package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.Service.Concretes.CustomerPolicyRenewalServiceImpl;
import intern.customer.agitoo.Service.Concretes.CustomerPolicyServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-policy-renewal/")
public class CustomerPolicyRenewalUIController {

    @Autowired
    private CustomerPolicyRenewalServiceImpl customerPolicyRenewalService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list customer policy renewals!");
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOList = customerPolicyRenewalService.getAll ();
        model.addAttribute ("renewals", customerPolicyRenewalDTOList);
        return "customer_policy_renewal/customer_policy_renewal_get_all";
    }
}
