package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.Issuer;
import intern.customer.agitoo.Service.Concretes.CustomerDebitCardServiceImpl;
import jakarta.servlet.http.HttpSession;
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
@RequestMapping("/ui/customer-debit-card/")
public class CustomerDebitCardUIController {

    @Autowired
    private CustomerDebitCardServiceImpl customerDebitCardService;

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
        log.info ("HTML - Received request to list customer claims!");
        List<CustomerDebitCardDTO> customerDebitCardDTOList = customerDebitCardService.getAll ().join ();
        model.addAttribute ("cards", customerDebitCardDTOList);
        return "customer_debit_card/customer_debit_card_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerDebitCard (
            @RequestParam(value = "customerId", required = false) Long customerId,
            Model model,
            CustomerDebitCardDTO customerDebitCardDTO
    ) {
        log.info ("HTML - Received request to CREATE company {}", customerDebitCardDTO);
        model.addAttribute ("customerDebitCardDTO", customerDebitCardDTO);
        model.addAttribute ("issuer", Issuer.values ());

        customerDebitCardDTO.setCustomerId (customerId);

//        model.addAttribute ("customerId", customerDebitCardDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder().customerId(customerId).build();
//        customerDebitCardDTO.setCustomer (customer);

        return "customer_debit_card/customer_debit_card_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerDebitCard (@ModelAttribute("customerDebitCardDTO") @Valid CustomerDebitCardDTO customerDebitCardDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", customerDebitCardDTO);

        Customer customer = Customer.builder ()
                .customerId (customerDebitCardDTO.getCustomerId ())
                .build ();
        customerDebitCardDTO.setCustomer (customer);

        CustomerDebitCardDTO savedCustomerDebitCard = customerDebitCardService.add (customerDebitCardDTO).join ();

//        Long createdCustomerDebitCardId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder().customerId (createdCustomerDebitCardId).build();
//        customerDebitCardDTO.setCustomer (customer);

        return "redirect:/ui/customer-payment/add?customerId=" + savedCustomerDebitCard.getCustomerId ();
    }

    @RequestMapping(value = "/update/{debitCardID}/edit", method = RequestMethod.GET)
    public String editCustomerDebitCard (@PathVariable("debitCardID") Long debitCardID, Model model) {
        CustomerDebitCardDTO claimFind = customerDebitCardService.findById (debitCardID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", debitCardID);
        model.addAttribute ("customerDebitCardDTO", claimFind);

        return "customer_debit_card/customer_debit_card_update";

    }


    @RequestMapping(value = "/update/{debitCardID}/edit", method = RequestMethod.POST)
    public String updateCustomerDebitCard (@PathVariable("debitCardID") Long debitCardID, @ModelAttribute("customerDebitCardDTO") @Valid CustomerDebitCardDTO customerDebitCardDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerDebitCardDTO);
        customerDebitCardDTO.setDebitCardID (debitCardID);
        CustomerDebitCardDTO updatedCustomerDebitCard = customerDebitCardService.update (customerDebitCardDTO).join ();
        model.addAttribute ("customerDebitCardDTO", updatedCustomerDebitCard);
        model.addAttribute ("issuer", Issuer.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerDebitCardDTO") CustomerDebitCardDTO customerDebitCardDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerDebitCardDTO);
        CustomerDebitCardDTO updatedCustomerDebitCard = customerDebitCardService.update (customerDebitCardDTO).join ();
        model.addAttribute ("customerDebitCardDTO", updatedCustomerDebitCard);
        return "redirect:/ui/customer-debit-card/get-all";
    }
}
