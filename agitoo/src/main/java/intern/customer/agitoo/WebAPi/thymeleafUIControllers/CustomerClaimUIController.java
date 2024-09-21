package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Models.enums.ClaimStatus;
import intern.customer.agitoo.Service.Concretes.CustomerClaimServiceImpl;
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerClaimDTO customerClaimDTO){
        log.info ("HTML - Received request to CREATE company {}", customerClaimDTO);
        model.addAttribute ("customerClaimDTO", customerClaimDTO);
        model.addAttribute ("claimStatus", ClaimStatus.values ());
        return "customer_claim/customer_claim_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerClaimDTO") CustomerClaimDTO customerClaimDTO){
        log.info ("HTML - SAVED customer {}", customerClaimDTO);
        customerClaimService.add (customerClaimDTO);

        return "redirect:/templates/home";
    }
}
