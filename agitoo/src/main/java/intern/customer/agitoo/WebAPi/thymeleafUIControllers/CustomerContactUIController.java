package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.Service.Concretes.CustomerContactServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-contact/")
public class CustomerContactUIController {

    @Autowired
    private CustomerContactServiceImpl customerContactService;

    @RequestMapping(value = "/get-all")
     public String getAllPage(Model model)
     {
         log.info ("HTML - Received request to list customer claims!");
         List<CustomerContactDTO> customerContactDTOList = customerContactService.getAll ();
         model.addAttribute ("contacts", customerContactDTOList);
         return "customer_contact/customer_contact_get_all";
     }
}
