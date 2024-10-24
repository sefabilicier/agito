package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCountryDTO;
import intern.customer.agitoo.Service.Concretes.CustomerAddressCountryServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-address-country/")
public class CustomerAddressCountryUIController {

    @Autowired
    private CustomerAddressCountryServiceImpl customerAddressCountryService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list address cities!");
        List<CustomerAddressCountryDTO> addressCountryDTOList = customerAddressCountryService.getAll ().join ();
        model.addAttribute ("countries", addressCountryDTOList);
        return "customer_address_country/customer_address_country_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerAddressCountry (Model model, CustomerAddressCountryDTO customerAddressCountryDTO) {
        log.info ("HTML - Received request to CREATE company {}", customerAddressCountryDTO);
        model.addAttribute ("customerAddressCountryDTO", customerAddressCountryDTO);
        return "customer_address_country/customer_address_country_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerAddressCountry (@ModelAttribute("customerAddressCountryDTO") @Valid CustomerAddressCountryDTO customerAddressCountryDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        log.info ("HTML - SAVED customer {}", customerAddressCountryDTO);

        CustomerAddressCountryDTO savedCustomerAddressCountryId = customerAddressCountryService.add (customerAddressCountryDTO).join ();
        redirectAttributes.addFlashAttribute ("customerAddressCountryId", savedCustomerAddressCountryId.getAddressCountryId ());
//        httpSession.setAttribute ("selectedCountryId", customerAddressCountryDTO.getAddressCountryId ());


        return "redirect:/ui/customer-address-city/add?customerAddressCountryId=" + savedCustomerAddressCountryId.getAddressCountryId ();
    }

    @RequestMapping(value = "/update/{addressCountryId}/edit", method = RequestMethod.GET)
    public String editCustomerAddressCountry (@PathVariable("addressCountryId") Long addressCountryId, Model model) {
        CustomerAddressCountryDTO countryFind = customerAddressCountryService.findById (addressCountryId).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", addressCountryId);
        model.addAttribute ("customerAddressCountryDTO", countryFind);
        return "customer_address_country/customer_address_country_update";
    }


    @RequestMapping(value = "/update/{addressCountryId}/edit", method = RequestMethod.POST)
    public String updateCustomerAddressCountry (@PathVariable("addressCountryId") Long addressCountryId, @ModelAttribute("customerAddressCountryDTO") @Valid CustomerAddressCountryDTO customerAddressCountryDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerAddressCountryDTO);
        customerAddressCountryDTO.setAddressCountryId (addressCountryId);
        CustomerAddressCountryDTO updatedCustomerAddressCountry = customerAddressCountryService.update (customerAddressCountryDTO).join ();
        model.addAttribute ("customerAddressCountryDTO", updatedCustomerAddressCountry);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerAddressCountryDTO") CustomerAddressCountryDTO customerAddressCountryDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerAddressCountryDTO);
        CustomerAddressCountryDTO updatedCustomerAddressCountry = customerAddressCountryService.update (customerAddressCountryDTO).join ();
        model.addAttribute ("customerAddressCountryDTO", updatedCustomerAddressCountry);
        return "redirect:/ui/customer-address-country/get-all";
    }

}
