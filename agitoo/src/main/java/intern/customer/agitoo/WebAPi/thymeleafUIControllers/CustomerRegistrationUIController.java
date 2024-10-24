package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerRegistrationDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.IsActive;
import intern.customer.agitoo.Service.Concretes.CustomerRegistrationServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-registration/")
public class CustomerRegistrationUIController {

    @Autowired
    private CustomerRegistrationServiceImpl customerRegistrationService;
//    @Autowired
//    private NotificationManager notificationManager;
//    @Autowired
//    private NotificationRule notificationRule;

    @InitBinder //Bu notasyon, bir metodu Spring MVC'nin veri bağlama sürecinde özel bir bağlayıcı olarak işaretler.
    public void initBinder (WebDataBinder binder) { //Bu metot, form verileri sunucuya gönderildiğinde çağrılır.
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        dateFormat.setLenient (false);
        binder.registerCustomEditor (
                Date.class,
                new CustomDateEditor (dateFormat, true));
    }

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list customer registrations!");
        List<CustomerRegistrationDTO> customerRegistrationDTOList = customerRegistrationService.getAll ().join ();

        model.addAttribute ("registrations", customerRegistrationDTOList);
        return "customer_registration/customer_registration_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerRegistration (
            @RequestParam(value = "customerId", required = false) Long customerId,
            Model model,
            CustomerRegistrationDTO customerRegistrationDTO
    ) {
        log.info ("HTML - Received request to CREATE customer registration {}", customerRegistrationDTO);
        model.addAttribute ("customerRegistrationDTO", customerRegistrationDTO);
        model.addAttribute ("activities", IsActive.values ());

        customerRegistrationDTO.setCustomerId (customerId);
//        model.addAttribute ("customerId", customerRegistrationDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder().customerId(customerId).build();
//        customerRegistrationDTO.setCustomer (customer);
        return "customer_registration/customer_registration_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerRegistration (@ModelAttribute("customerRegistrationDTO") @Valid CustomerRegistrationDTO customerRegistrationDTO) {
        log.info ("HTML - SAVED customer {}", customerRegistrationDTO);


        Customer customer = Customer.builder ().customerId (
                        customerRegistrationDTO.getCustomerId ())
                .build ();
        customerRegistrationDTO.setCustomer (customer);


        CustomerRegistrationDTO savedCustomerRegistration = customerRegistrationService.add (customerRegistrationDTO).join ();

//        PersonDTO personDTO = null;
//        CompanyDTO companyDTO = null;
//        CustomerDTO customerDTO = null;
//
//
//        if (CustomerType.Person.equals(customerDTO.getCustomerType())) {
//            personDTO.setFullName(personDTO.getFullName()); // Örnek atama
//        } else if (CustomerType.Company.equals (customerDTO.getCustomerType ())) {
//            companyDTO.setCompanyName(companyDTO.getCompanyName()); // Örnek atama
//        }

//        NotificationRule.sendNotification(customerDTO, personDTO, companyDTO);


        return "redirect:/ui/customer-claim/add?customerId=" + savedCustomerRegistration.getCustomerId ();
    }

    @RequestMapping(value = "/update/{registrationID}/edit", method = RequestMethod.GET)
    public String editCustomerRegistration (@PathVariable Long registrationID, Model model) {
        CustomerRegistrationDTO registrationFind = customerRegistrationService.findById (registrationID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", registrationID);
        model.addAttribute ("customerRegistrationDTO", registrationFind);
        return "customer_registration/customer_registration_update";
    }


    @RequestMapping(value = "/update/{registrationID}/edit", method = RequestMethod.POST)
    public String updateCustomerRegistration (@PathVariable Long registrationID, @ModelAttribute("customerRegistrationDTO") @Valid CustomerRegistrationDTO customerRegistrationDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerRegistrationDTO);
        customerRegistrationDTO.setRegistrationID (registrationID);
        CustomerRegistrationDTO updatedCustomerRegistration = customerRegistrationService.update (customerRegistrationDTO).join ();
        model.addAttribute ("customerRegistrationDTO", updatedCustomerRegistration);
        model.addAttribute ("activities", IsActive.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerRegistrationDTO") CustomerRegistrationDTO customerRegistrationDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerRegistrationDTO);
        CustomerRegistrationDTO updatedCustomerRegistration = customerRegistrationService.update (customerRegistrationDTO).join ();
        model.addAttribute ("customerRegistrationDTO", updatedCustomerRegistration);
        return "redirect:/ui/customer-registration/get-all";
    }

}