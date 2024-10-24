package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerAddressDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCity;
import intern.customer.agitoo.Models.Concretes.CustomerAddressCountry;
import intern.customer.agitoo.Models.enums.AddressType;
import intern.customer.agitoo.Models.enums.IsActive;
import intern.customer.agitoo.Service.Concretes.CustomerAddressServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-address/")
public class CustomerAddressUIController {

    @Autowired
    private CustomerAddressServiceImpl customerAddressService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list addresses!");
        List<CustomerAddressDTO> addressDTOList = customerAddressService.getAll ().join ();
        model.addAttribute ("addresses", addressDTOList);
        return "customer_address/customer_address_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerAddress (
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "customerAddressCityId", required = false) Long customerAddressCityId,
            @RequestParam(value = "customerAddressCountryId", required = false) Long customerAddressCountryId,
            Model model,
            CustomerAddressDTO customerAddressDTO
    ) {
        log.info ("HTML - Received request to CREATE company branch {}", customerAddressDTO);
        model.addAttribute ("customerAddressDTO", customerAddressDTO);
        model.addAttribute ("addressType", AddressType.values ());
        model.addAttribute ("default", IsActive.values ());

        customerAddressDTO.setCustomerId (customerId);

//        model.addAttribute ("customerId", customerAddressDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder().customerId(customerId).build();
//        customerAddressDTO.setCustomer (customer);

        customerAddressDTO.setCustomerAddressCityId (customerAddressCityId);

//        model.addAttribute ("customerAddressCityId", customerAddressDTO.getCustomerAddressCity().getCityID ()); //TODO adding to HTML
//        CustomerAddressCity customerAddressCity = CustomerAddressCity.builder ().cityID (customerAddressCityId).build ();
//        customerAddressDTO.setCustomerAddressCity (customerAddressCity);

        customerAddressDTO.setCustomerAddressCountryId (customerAddressCountryId);

//        model.addAttribute ("customerAddressCountryId", customerAddressDTO.getCustomerAddressCountry ().getAddressCountryId ()); //TODO adding to HTML
//        CustomerAddressCountry customerAddressCountry = CustomerAddressCountry.builder ().addressCountryId (customerAddressCountryId).build ();
//        customerAddressDTO.setCustomerAddressCountry (customerAddressCountry);

        return "customer_address/customer_address_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerAddress (
            @ModelAttribute("customerAddressDTO") @Valid CustomerAddressDTO customerAddressDTO,
            HttpSession httpSession
    ) {
        log.info ("HTML - SAVED customer {}", customerAddressDTO);

        Customer customer = Customer.builder ()
                .customerId (customerAddressDTO.getCustomerId ())
                .build ();
        customerAddressDTO.setCustomer (customer);


        CustomerAddressCity customerCity = CustomerAddressCity.builder ()
                .cityID (customerAddressDTO.getCustomerAddressCityId ())
                .build ();
        customerAddressDTO.setCustomerAddressCity (customerCity);


        CustomerAddressCountry customerCountry = CustomerAddressCountry.builder ()
                .addressCountryId (customerAddressDTO.getCustomerAddressCountryId ())
                .build ();
        customerAddressDTO.setCustomerAddressCountry (customerCountry);

        CustomerAddressDTO savedCustomerAddress = customerAddressService.add (customerAddressDTO).join ();

//        Long createdCustomerAddressId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder ().customerId (createdCustomerAddressId).build ();
//        customerAddressDTO.setCustomer (customer);
//
//        Long createdCustomerAddressId4Country = (Long) httpSession.getAttribute ("selectedCountryId");
//        CustomerAddressCountry customerAddressCountry = CustomerAddressCountry.builder ().addressCountryId (createdCustomerAddressId4Country).build ();
//        customerAddressDTO.setCustomerAddressCountry (customerAddressCountry);
//
//        Long createdCustomerAddressId4City = (Long) httpSession.getAttribute ("selectedCityId");
//        CustomerAddressCity customerAddressCity = CustomerAddressCity.builder ().cityID (createdCustomerAddressId4City).build ();
//        customerAddressDTO.setCustomerAddressCity (customerAddressCity);

        return "redirect:/ui/customer-address-country/add?customerId=" + savedCustomerAddress.getCustomerId ();
    }

    @RequestMapping(value = "/update/{addressID}/edit", method = RequestMethod.GET)
    public String editCustomerAddress (@PathVariable("addressID") Long addressID, Model model) {
        CustomerAddressDTO addressFind = customerAddressService.findById (addressID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", addressID);
        model.addAttribute ("customerAddressDTO", addressFind);
        return "customer_address/customer_address_update";

    }


    @RequestMapping(value = "/update/{addressID}/edit", method = RequestMethod.POST)
    public String updateCustomerAddress (@PathVariable("addressID") Long addressID, @ModelAttribute("customerAddressDTO") @Valid CustomerAddressDTO customerAddressDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerAddressDTO);
        customerAddressDTO.setAddressID (addressID);
        CustomerAddressDTO updatedCustomerAddress = customerAddressService.update (customerAddressDTO).join ();
        model.addAttribute ("customerAddressDTO", updatedCustomerAddress);
        model.addAttribute ("addressType", AddressType.values ());
        model.addAttribute ("default", IsActive.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerAddressDTO") CustomerAddressDTO customerAddressDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerAddressDTO);
        CustomerAddressDTO updatedCustomerAddress = customerAddressService.update (customerAddressDTO).join ();
        model.addAttribute ("customerAddressDTO", updatedCustomerAddress);
        return "redirect:/ui/customer-address/get-all";
    }
}
