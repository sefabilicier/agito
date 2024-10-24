package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
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

import java.security.Principal;
import java.util.List;

import static intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules.customerTypeRedirection.selectedCustomerType;

@Slf4j
@Controller
//@Controller and @ResponseBody both in one --> @RestController: Combines @Controller and @ResponseBody, which means it returns data (like JSON) directly, bypassing view resolution.
@RequestMapping("/ui/customer/")
public class CustomerUIController {

    @Autowired
    private CustomerServiceImpl customerService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model, Principal principal) {
        log.info ("HTML - Received request to list customers! --> " + principal.getName ());
        List<CustomerDTO> customerDTOList = customerService.getAll ().join ();
        //countCompanyType();

        model.addAttribute ("customers", customerDTOList);
        return "customer/customer_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomer (Model model, CustomerDTO customerDTO) {
        log.info ("HTML - Received request to CREATE customer {}", customerDTO);
        model.addAttribute ("customer_add", CustomerType.values ());
        model.addAttribute ("customerDTO", customerDTO);
        return "customer/customer_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomer (@ModelAttribute("customerDTO") @Valid CustomerDTO customerDTO, Model model, RedirectAttributes redirectAttributes) {
        log.info ("HTML - SAVED customer {}", customerDTO);

        CustomerDTO savedCustomer = customerService.add (customerDTO).join ();
        redirectAttributes.addFlashAttribute ("customerId", savedCustomer.getCustomerId ());

        return selectedCustomerType (
                customerDTO,
                "redirect:/ui/person/add?customerId=" + savedCustomer.getCustomerId (),
                "redirect:/ui/company/add?customerId=" + savedCustomer.getCustomerId (),
                "redirect:/templates/home"
        );
    }

    @RequestMapping(value = "/update/{customerId}/edit", method = RequestMethod.GET)
    public String editCustomer (
            @PathVariable("customerId") Long customerId,
            Model model,
            CustomerDTO customerDTO) {
        log.info ("HTML - Received request to EDIT customer with ID {}", customerId);
        CustomerDTO customerFind = customerService.findById (customerId).join ();
        model.addAttribute ("customerDTO", customerFind);
        return "customer/customer_update";

    }

    @RequestMapping(value = "/update/{customerId}/edit", method = RequestMethod.POST)
    public String updateCustomer (@PathVariable("customerId") Long customerId,
                                  @ModelAttribute("customerDTO") @Valid CustomerDTO customerDTO,
                                  Model model
    ) {
        log.info ("HTML - Received request to UPDATE company branch  {}", customerDTO);
        customerDTO.setCustomerId (customerId);
        CustomerDTO updatedCustomer = customerService.update (customerDTO).join ();
        model.addAttribute ("customer_add", CustomerType.values ());
        model.addAttribute ("customerDTO", updatedCustomer);
        return "redirect:/updating";
    }

    @RequestMapping(value = "/update/{customerId}", method = RequestMethod.POST)
    // Should be POST instead of PUT for form submissions
    public String updateCustomer (@ModelAttribute("customerDTO") CustomerDTO customerDTO, Model model) {
        log.info ("HTML - Received request to UPDATE customer {}", customerDTO);
        CustomerDTO updatedCustomer = customerService.update (customerDTO).join ();
        model.addAttribute ("customerDTO", updatedCustomer);
        return "redirect:/ui/customer/get-all";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerDTO") CustomerDTO customerDTO, Model model) {
        log.info ("HTML - Confirming update for customer {}", customerDTO);
        CustomerDTO updatedCustomer = customerService.update (customerDTO).join ();
        model.addAttribute ("customerDTO", updatedCustomer);
        return "redirect:/ui/customer/get-all";
    }

}