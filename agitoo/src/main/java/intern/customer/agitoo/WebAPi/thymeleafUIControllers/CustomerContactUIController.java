package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.CustomerContactType;
import intern.customer.agitoo.Service.Concretes.CustomerContactServiceImpl;
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
@RequestMapping("/ui/customer-contact/")
public class CustomerContactUIController {

    @Autowired
    private CustomerContactServiceImpl customerContactService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list customer claims!");
        List<CustomerContactDTO> customerContactDTOList = customerContactService.getAll ().join ();
        model.addAttribute ("contacts", customerContactDTOList);
        return "customer_contact/customer_contact_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerContact (@RequestParam(value = "customerId", required = false) Long customerId, Model model, CustomerContactDTO customerContactDTO) {
        log.info ("HTML - Received request to CREATE company {}", customerContactDTO);
        model.addAttribute ("customerContactDTO", customerContactDTO);
        model.addAttribute ("contactType", CustomerContactType.values ());

//        model.addAttribute ("customerId", customerContactDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder ().customerId (customerId).build ();
//        customerContactDTO.setCustomer (customer);

        customerContactDTO.setCustomerId (customerId);

        return "customer_contact/customer_contact_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerContact (@ModelAttribute("customerContactDTO") @Valid CustomerContactDTO customerContactDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", customerContactDTO);

        Customer customer = Customer.builder ()
                .customerId (customerContactDTO.getCustomerId ())
                .build ();
        customerContactDTO.setCustomer (customer);

        CustomerContactDTO savedCustomerContact = customerContactService.add (customerContactDTO).join ();

//        Long createdCustomerContactId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder ().customerId (createdCustomerContactId).build ();
//        customerContactDTO.setCustomer (customer);

        return "redirect:/ui/customer-address/add?customerId=" + savedCustomerContact.getCustomerId ();
    }

    @RequestMapping(value = "/update/{contactID}/edit", method = RequestMethod.GET)
    public String editCustomerContact (@PathVariable("contactID") Long contactID, Model model) {
        CustomerContactDTO contactFind = customerContactService.findById (contactID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", contactID);
        model.addAttribute ("customerContactDTO", contactFind);
        return "customer_contact/customer_contact_update";

    }

    @RequestMapping(value = "/update/{contactID}/edit", method = RequestMethod.POST)
    public String updateCustomerContact (
            @PathVariable("contactID") Long contactID,
            @ModelAttribute("customerContactDTO") @Valid CustomerContactDTO customerContactDTO,
            Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerContactDTO);
        customerContactDTO.setContactID (contactID);
        CustomerContactDTO updatedCustomerContact = customerContactService.update (customerContactDTO).join ();
        model.addAttribute ("customerContactDTO", updatedCustomerContact);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerContactDTO") CustomerContactDTO customerContactDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerContactDTO);
        CustomerContactDTO updatedCustomerContact = customerContactService.update (customerContactDTO).join ();
        model.addAttribute ("customerContactDTO", updatedCustomerContact);
        return "redirect:/ui/customer-contact/get-all";
    }
}
