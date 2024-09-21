package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerContactDTO;
import intern.customer.agitoo.DTO.DTOs.CustomerDebitCardDTO;
import intern.customer.agitoo.Models.enums.Issuer;
import intern.customer.agitoo.Service.Concretes.CustomerContactServiceImpl;
import intern.customer.agitoo.Service.Concretes.CustomerDebitCardServiceImpl;
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
@RequestMapping("/ui/customer-debit-card/")
public class CustomerDebitCardUIController {

    @Autowired
    private CustomerDebitCardServiceImpl customerDebitCardService;

    @RequestMapping(value = "/get-all")
    public String getAllPage(Model model)
    {
        log.info ("HTML - Received request to list customer claims!");
        List<CustomerDebitCardDTO> customerDebitCardDTOList = customerDebitCardService.getAll ();
        model.addAttribute ("cards", customerDebitCardDTOList);
        return "customer_debit_card/customer_debit_card_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, CustomerDebitCardDTO customerDebitCardDTO){
        log.info ("HTML - Received request to CREATE company {}", customerDebitCardDTO);
        model.addAttribute ("customerDebitCardDTO", customerDebitCardDTO);
        model.addAttribute ("issuer", Issuer.values ());
        return "customer_debit_card/customer_debit_card_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("customerDebitCardDTO") CustomerDebitCardDTO customerDebitCardDTO){
        log.info ("HTML - SAVED customer {}", customerDebitCardDTO);
        customerDebitCardService.add (customerDebitCardDTO);

        return "redirect:/ui/customer-payment/add";
    }
}
