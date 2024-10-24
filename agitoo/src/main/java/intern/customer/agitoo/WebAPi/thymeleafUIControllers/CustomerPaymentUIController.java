package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Models.enums.PaymentMethod;
import intern.customer.agitoo.Service.Concretes.CustomerPaymentServiceImpl;
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
@RequestMapping("/ui/customer-payment/")
public class CustomerPaymentUIController {

    @Autowired
    private CustomerPaymentServiceImpl customerPaymentService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list customer payments!");
        List<CustomerPaymentDTO> customerPaymentDTOList = customerPaymentService.getAll ().join ();
        model.addAttribute ("payments", customerPaymentDTOList);
        return "customer_payment/customer_payment_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerPayment (
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "customerPolicyId", required = false) Long customerPolicyId,
            Model model,
            CustomerPaymentDTO customerPaymentDTO
    ) {
        log.info ("HTML - Received request to CREATE company {}", customerPaymentDTO);
        model.addAttribute ("customerPaymentDTO", customerPaymentDTO);
        model.addAttribute ("paymentMethod", PaymentMethod.values ());

        customerPaymentDTO.setCustomerId (customerId);
        customerPaymentDTO.setCustomerPolicyId (customerPolicyId);

//        model.addAttribute ("customerId", customerPaymentDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//        Customer customer = Customer.builder().customerId(customerId).build();
//        customerPaymentDTO.setCustomer (customer);
//
//        model.addAttribute ("customerPolicyId", customerPaymentDTO.getCustomerPolicy ().getCustomerPolicyId ()); //TODO adding to HTML
//        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (customerPolicyId).build ();
//        customerPaymentDTO.setCustomerPolicy (customerPolicy);

        return "customer_payment/customer_payment_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerPayment (@ModelAttribute("customerPaymentDTO") @Valid CustomerPaymentDTO customerPaymentDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", customerPaymentDTO);

        Customer customer = Customer.builder ()
                .customerId (customerPaymentDTO.getCustomerId ())
                .build ();
        customerPaymentDTO.setCustomer (customer);

        CustomerPolicy customerPolicy = CustomerPolicy.builder ()
                .customerPolicyId (customerPaymentDTO.getCustomerPolicyId ())
                .build ();
        customerPaymentDTO.setCustomerPolicy (customerPolicy);

        CustomerPaymentDTO savedCustomerPayment = customerPaymentService.add (customerPaymentDTO).join ();

//        Long createdCustomerPaymentId4Customer = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder().customerId (createdCustomerPaymentId4Customer).build();
//        customerPaymentDTO.setCustomer (customer);
//
//        Long createdCustomerPaymentId4Policy = (Long) httpSession.getAttribute ("selectedPolicyId");
//        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (createdCustomerPaymentId4Policy).build ();
//        customerPaymentDTO.setCustomerPolicy (customerPolicy);

        return "redirect:/ui/customer-registration/add?customerId=" + savedCustomerPayment.getCustomerId ();
    }

    @RequestMapping(value = "/update/{paymentID}/edit", method = RequestMethod.GET)
    public String editCustomerPayment (@PathVariable("paymentID") Long paymentID, Model model) {
        CustomerPaymentDTO paymentFind = customerPaymentService.findById (paymentID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", paymentID);
        model.addAttribute ("customerPaymentDTO", paymentFind);
        return "customer_payment/customer_payment_update";
    }


    @RequestMapping(value = "/update/{paymentID}/edit", method = RequestMethod.POST)
    public String updateCustomerPayment (@PathVariable("paymentID") Long paymentID, @ModelAttribute("customerPaymentDTO") @Valid CustomerPaymentDTO customerPaymentDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerPaymentDTO);
        customerPaymentDTO.setPaymentID (paymentID);
        CustomerPaymentDTO updatedCustomerPayment = customerPaymentService.update (customerPaymentDTO).join ();
        model.addAttribute ("customerPaymentDTO", updatedCustomerPayment);
        model.addAttribute ("paymentMethod", PaymentMethod.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerPaymentDTO") CustomerPaymentDTO customerPaymentDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerPaymentDTO);
        CustomerPaymentDTO updatedCustomerPayment = customerPaymentService.update (customerPaymentDTO).join ();
        model.addAttribute ("customerPaymentDTO", updatedCustomerPayment);
        return "redirect:/ui/customer-payment/get-all";
    }

}
