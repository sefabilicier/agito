package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.Service.Concretes.CustomerAddressCountryServiceImpl;
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
@RequestMapping("/ui/customer-address-country/")
public class CustomerAddressCountryUIController {

    @Autowired
    private CustomerAddressCountryServiceImpl customerAddressCountryService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model)
    {
        log.info ("HTML - Received request to list address cities!");
        List<CustomerAddressCountryDTO> addressCountryDTOList = customerAddressCountryService.getAll ();
        model.addAttribute ("countries", addressCountryDTOList);
        return "customer_address_country/customer_address_country_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerAddressCountryDTO customerAddressCountryDTO){
        log.info ("HTML - Received request to CREATE company {}", customerAddressCountryDTO);
        model.addAttribute ("customerAddressCountryDTO", customerAddressCountryDTO);
        return "customer_address_country/customer_address_country_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerAddressCountryDTO") CustomerAddressCountryDTO customerAddressCountryDTO){
        log.info ("HTML - SAVED customer {}", customerAddressCountryDTO);
        customerAddressCountryService.add (customerAddressCountryDTO);

        return "redirect:/ui/customer-address-city/add";
    }

}
