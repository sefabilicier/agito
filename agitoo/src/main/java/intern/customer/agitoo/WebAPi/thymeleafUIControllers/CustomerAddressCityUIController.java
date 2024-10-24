package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressCityDTO;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Service.Concretes.CustomerAddressCityServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-address-city/")
public class CustomerAddressCityUIController {

    @Autowired
    private CustomerAddressCityServiceImpl customerAddressCityService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list address cities!");
        List<CustomerAddressCityDTO> customerAddressCityDTOList = customerAddressCityService.getAll ().join ();
        model.addAttribute ("cities", customerAddressCityDTOList);
        return "customer_address_city/customer_address_city_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerAddressCity (
            @RequestParam(value = "customerAddressCountryId", required = false) Long customerAddressCountryId,
            Model model,
            CustomerAddressCityDTO customerAddressCityDTO) {
        log.info ("HTML - Received request to CREATE company {}", customerAddressCityDTO);
        model.addAttribute ("customerAddressCityDTO", customerAddressCityDTO);

        customerAddressCityDTO.setCustomerAddressCountryId (customerAddressCountryId);

//        model.addAttribute ("customerAddressCountryId", customerAddressCityDTO.getCustomerAddressCountry ().getAddressCountryId ()); //TODO : adding to HTML
//        CustomerAddressCountry customerAddressCountry = CustomerAddressCountry.builder().addressCountryId (customerAddressCountryId).build ();
//        customerAddressCityDTO.setCustomerAddressCountry (customerAddressCountry);

        return "customer_address_city/customer_address_city_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerAddressCity (@ModelAttribute("customerAddressCityDTO") @Valid CustomerAddressCityDTO customerAddressCityDTO, HttpSession httpSession, RedirectAttributes redirectAttributes) {
        log.info ("HTML - SAVED customer {}", customerAddressCityDTO);


        CustomerAddressCountry customerCountry = CustomerAddressCountry.builder ()
                .addressCountryId (customerAddressCityDTO.getCustomerAddressCountryId ())
                .build ();
        customerAddressCityDTO.setCustomerAddressCountry (customerCountry);

        CustomerAddressCityDTO savedCustomerAddressCityId = customerAddressCityService.add (customerAddressCityDTO).join ();
        redirectAttributes.addFlashAttribute ("customerAddressCityId", savedCustomerAddressCityId.getCityID ()); //todo

//        httpSession.setAttribute ("selectedCityId", customerAddressCityDTO.getCityID ());
//
//        Long createdCustomerAddressCityId = (Long) httpSession.getAttribute ("selectedCountryId");
//        CustomerAddressCountry customerAddressCountry = CustomerAddressCountry.builder ().addressCountryId (createdCustomerAddressCityId).build ();
//        customerAddressCityDTO.setCustomerAddressCountry (customerAddressCountry);

        return "redirect:/ui/customer-policy/add?customerId=" + customerAddressCityDTO; //TODO : WHAT HAPPENS HERE
    }

    @RequestMapping(value = "/update/{cityID}/edit", method = RequestMethod.GET)
    public String editCustomerAddressCity (@PathVariable("cityID") Long cityID, Model model) {
        CustomerAddressCityDTO cityFind = customerAddressCityService.findById (cityID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", cityID);
        model.addAttribute ("customerAddressCityDTO", cityFind);
        return "customer_address_city/customer_address_city_update";

    }


    @RequestMapping(value = "/update/{cityID}/edit", method = RequestMethod.POST)
    public String updateCustomerAddressCity (@PathVariable("cityID") Long cityID, @ModelAttribute("customerAddressCityDTO") @Valid CustomerAddressCityDTO customerAddressCityDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerAddressCityDTO);
        customerAddressCityDTO.setCityID (cityID);
        CustomerAddressCityDTO updatedCustomerAddressCity = customerAddressCityService.update (customerAddressCityDTO).join ();
        model.addAttribute ("customerAddressCityDTO", updatedCustomerAddressCity);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerAddressCityDTO") CustomerAddressCityDTO customerAddressCityDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerAddressCityDTO);
        CustomerAddressCityDTO updatedCustomerAddressCity = customerAddressCityService.update (customerAddressCityDTO).join ();
        model.addAttribute ("customerAddressCityDTO", updatedCustomerAddressCity);
        return "redirect:/ui/customer-address-city/get-all";
    }
}
