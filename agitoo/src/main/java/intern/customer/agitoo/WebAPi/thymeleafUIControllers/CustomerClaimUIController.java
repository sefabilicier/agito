package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Service.Concretes.CustomerClaimServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-claim/")
public class CustomerClaimUIController {

    @Autowired
    private CustomerClaimServiceImpl customerClaimService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list customer claims!");
        List<CustomerClaimDTO> customerClaimDTOList = customerClaimService.getAll ();
        model.addAttribute ("claims", customerClaimDTOList);
        return "customer_claim/customer_claim_get_all";
    }
}
