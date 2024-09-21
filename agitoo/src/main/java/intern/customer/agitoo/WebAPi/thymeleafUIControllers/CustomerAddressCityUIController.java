package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.Service.Concretes.CustomerAddressCityServiceImpl;
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
@RequestMapping("/ui/customer-address-city/")
public class CustomerAddressCityUIController {

    @Autowired
    private CustomerAddressCityServiceImpl customerAddressCityService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model){
        log.info ("HTML - Received request to list address cities!");
        List<CustomerAddressCityDTO> customerAddressCityDTOList = customerAddressCityService.getAll ();
        model.addAttribute ("cities", customerAddressCityDTOList);
        return "customer_address_city/customer_address_city_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerAddressCityDTO customerAddressCityDTO){
        log.info ("HTML - Received request to CREATE company {}", customerAddressCityDTO);
        model.addAttribute ("customerAddressCityDTO", customerAddressCityDTO);
        return "customer_address_city/customer_address_city_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerAddressCityDTO") CustomerAddressCityDTO customerAddressCityDTO){
        log.info ("HTML - SAVED customer {}", customerAddressCityDTO);
        customerAddressCityService.add (customerAddressCityDTO);

        return "redirect:/ui/customer-policy/add";
    }
}
