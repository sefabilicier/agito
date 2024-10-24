package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.CustomerPolicyType;
import intern.customer.agitoo.Service.Concretes.CustomerPolicyServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/customer-policy/")
public class CustomerPolicyUIController {

    @Autowired
    private CustomerPolicyServiceImpl customerPolicyService;

    @InitBinder //Bu notasyon, bir metodu Spring MVC'nin veri bağlama sürecinde özel bir bağlayıcı olarak işaretler.
    public void initBinder (WebDataBinder binder) { //Bu metot, form verileri sunucuya gönderildiğinde çağrılır.
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd");
        dateFormat.setLenient (false);
        binder.registerCustomEditor (
                Date.class,
                new CustomDateEditor (dateFormat, true));
    }
    //{"message":"Validation exception","validationErrors":{"renewalDate":"Failed to convert property value of type 'java.lang.String' to required type 'java.util.Date' for property 'renewalDate'; Failed to convert from type [java.lang.String] to type [@jakarta.validation.constraints.NotNull @jakarta.validation.constraints.FutureOrPresent java.util.Date] for value [2024-10-31]"}}

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list customer policies!");
        List<CustomerPolicyDTO> customerPolicyDTOList = customerPolicyService.getAll ().join ();
        model.addAttribute ("policies", customerPolicyDTOList);
        return "customer_policy/customer_policy_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerPolicy (@RequestParam(value = "customerId", required = false) Long customerId, Model model, CustomerPolicyDTO customerPolicyDTO) {
        log.info ("HTML - Received request to CREATE company {}", customerPolicyDTO);
        model.addAttribute ("customerPolicyDTO", customerPolicyDTO);
        model.addAttribute ("customerPolicyType", CustomerPolicyType.values ());

//        model.addAttribute ("customerId", customerPolicyDTO.getCustomer ().getCustomerId ()); //TODO adding to HTML
//
//        Customer customer = Customer.builder().customerId(customerId).build();
//        customerPolicyDTO.setCustomer (customer);
        customerPolicyDTO.setCustomerId (customerId);

        return "customer_policy/customer_policy_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerPolicy (@ModelAttribute("customerPolicyDTO") CustomerPolicyDTO customerPolicyDTO, HttpSession httpSession, RedirectAttributes redirectAttributes, Model model) {
        log.info ("HTML - SAVED customer {}", customerPolicyDTO);


        Customer customerPolicy = Customer.builder ().customerId (
                        customerPolicyDTO.getCustomerId ())
                .build ();
        customerPolicyDTO.setCustomer (customerPolicy);

        CustomerPolicyDTO savedCustomerPolicyId = customerPolicyService.add (customerPolicyDTO).join ();
        redirectAttributes.addFlashAttribute ("customerPolicyId", savedCustomerPolicyId.getCustomerPolicyId ());


//        httpSession.setAttribute ("selectedPolicyId", customerPolicyDTO.getCustomerPolicyId ());
//
//        Long createdCustomerPolicyId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder().customerId (createdCustomerPolicyId).build();
//        customerPolicyDTO.setCustomer (customer);

        return "redirect:/ui/customer-policy-renewal/add?customerPolicyId=" + savedCustomerPolicyId.getCustomerId ();
    }

    @RequestMapping(value = "/update/{customerPolicyId}/edit", method = RequestMethod.GET)
    public String editCustomerPolicy (@PathVariable("customerPolicyId") Long customerPolicyId, Model model) {
        log.info ("HTML - Received request to EDIT person with ID {}", customerPolicyId);
        CustomerPolicyDTO policyFind = customerPolicyService.findById (customerPolicyId).join ();

        model.addAttribute ("customerPolicyDTO", policyFind);
        return "customer_policy/customer_policy_update";
    }


    @RequestMapping(value = "/update/{customerPolicyId}/edit", method = RequestMethod.POST)
    public String updateCustomerPolicy (
            @PathVariable("customerPolicyId") Long customerPolicyId,
            @ModelAttribute("customerPolicyDTO") CustomerPolicyDTO customerPolicyDTO,
            Model model
    ) {
        log.info ("HTML - Received request to UPDATE person {}", customerPolicyDTO);
        customerPolicyDTO.setCustomerPolicyId (customerPolicyId);
        CustomerPolicyDTO updatedCustomerPolicy = customerPolicyService.update (customerPolicyDTO).join ();
        model.addAttribute ("customerPolicyDTO", updatedCustomerPolicy);
        model.addAttribute ("customerPolicyType", CustomerPolicyType.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerPolicyDTO") CustomerPolicyDTO customerPolicyDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerPolicyDTO);
        CustomerPolicyDTO updatedCustomerPolicy = customerPolicyService.update (customerPolicyDTO).join ();
        model.addAttribute ("customerPolicyDTO", updatedCustomerPolicy);
        return "redirect:/ui/customer-policy/get-all";
    }
}
