package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Service.Concretes.CustomerPaymentServiceImpl;
import intern.customer.agitoo.Service.Concretes.CustomerRegistrationServiceImpl;
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

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerRegistrationDTO customerRegistrationDTO){
        log.info ("HTML - Received request to CREATE customer registration {}", customerRegistrationDTO);
        model.addAttribute ("customerRegistrationDTO", customerRegistrationDTO);
        return "customer_registration/customer_registration_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerRegistrationDTO") CustomerRegistrationDTO customerRegistrationDTO){
        log.info ("HTML - SAVED customer {}", customerRegistrationDTO);
        customerRegistrationService.add (customerRegistrationDTO);

        return "redirect:/templates/home"; //TODO : CUSTOMER RECAP PAGE - SHOWS THE SUM OF HIS/HER
    }

}

