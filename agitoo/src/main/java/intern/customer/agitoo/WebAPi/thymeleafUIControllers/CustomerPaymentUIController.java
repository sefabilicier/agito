package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerPaymentDTO;
import intern.customer.agitoo.Models.enums.PaymentMethod;
import intern.customer.agitoo.Service.Concretes.CustomerPaymentServiceImpl;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
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
@RequestMapping("/ui/customer-payment/")
public class CustomerPaymentUIController {

    @Autowired
    private CustomerPaymentServiceImpl customerPaymentService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list customer payments!");
        List<CustomerPaymentDTO> customerPaymentDTOList = customerPaymentService.getAll();

        model.addAttribute("payments", customerPaymentDTOList);
        return "customer_payment/customer_payment_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerPaymentDTO customerPaymentDTO){
        log.info ("HTML - Received request to CREATE company {}", customerPaymentDTO);
        model.addAttribute ("customerPaymentDTO", customerPaymentDTO);
        model.addAttribute ("paymentMethod", PaymentMethod.values ());
        return "customer_payment/customer_payment_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerPaymentDTO") CustomerPaymentDTO customerPaymentDTO){
        log.info ("HTML - SAVED customer {}", customerPaymentDTO);
        customerPaymentService.add (customerPaymentDTO);

        return "redirect:/ui/customer-registration/add";
    }

}
