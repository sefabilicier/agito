package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Service.Concretes.CustomerPaymentServiceImpl;
import intern.customer.agitoo.Service.Concretes.CustomerRegistrationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-registration/")
public class CustomerRegistrationUIController {

    @Autowired
    private CustomerRegistrationServiceImpl customerRegistrationService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list customer registrations!");
        List<CustomerRegistrationDTO> customerRegistrationDTOList = customerRegistrationService.getAll();

        model.addAttribute("registrations", customerRegistrationDTOList);
        return "customer_registration/customer_registration_get_all";
    }

}

