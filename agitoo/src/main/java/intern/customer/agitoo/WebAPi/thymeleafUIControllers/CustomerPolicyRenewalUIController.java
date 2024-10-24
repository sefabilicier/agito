package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerPolicyRenewalDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Service.Concretes.CustomerPolicyRenewalServiceImpl;
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
@RequestMapping("/ui/customer-policy-renewal/")
public class CustomerPolicyRenewalUIController {

    @Autowired
    private CustomerPolicyRenewalServiceImpl customerPolicyRenewalService;

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
        log.info ("HTML - Received request to list customer policy renewals!");
        List<CustomerPolicyRenewalDTO> customerPolicyRenewalDTOList = customerPolicyRenewalService.getAll ().join ();
        model.addAttribute ("renewals", customerPolicyRenewalDTOList);
        return "customer_policy_renewal/customer_policy_renewal_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerPolicyRenewal (
            @RequestParam(value = "customerPolicyId", required = false) Long customerPolicyId,
            Model model,
            CustomerPolicyRenewalDTO customerPolicyRenewalDTO
    ) {
        log.info ("HTML - Received request to CREATE company {}", customerPolicyRenewalDTO);
        model.addAttribute ("customerPolicyRenewalDTO", customerPolicyRenewalDTO);

        customerPolicyRenewalDTO.setCustomerPolicyId (customerPolicyId);

//        model.addAttribute ("customerPolicyId", customerPolicyRenewalDTO.getCustomerPolicy ().getCustomerPolicyId ()); //TODO : adding to HTML
//        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (customerPolicyId).build ();
//        customerPolicyRenewalDTO.setCustomerPolicy (customerPolicy);

        return "customer_policy_renewal/customer_policy_renewal_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerPolicyRenewal (@ModelAttribute("customerPolicyRenewalDTO") @Valid CustomerPolicyRenewalDTO customerPolicyRenewalDTO) {
        log.info ("HTML - SAVED customer {}", customerPolicyRenewalDTO);

        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (
                        customerPolicyRenewalDTO.getCustomerPolicyId ())
                .build ();
        customerPolicyRenewalDTO.setCustomerPolicy (customerPolicy);

        CustomerPolicyRenewalDTO savedCustomerPolicyRenewal = customerPolicyRenewalService.add (customerPolicyRenewalDTO).join ();

        return "redirect:/ui/customer-debit-card/add?customerId=" + savedCustomerPolicyRenewal.getCustomerPolicy ().getCustomer ().getCustomerId ();
    }

    @RequestMapping(value = "/update/{renewalId}/edit", method = RequestMethod.GET)
    public String editCustomerPolicyRenewal (@PathVariable("renewalId") Long renewalId, Model model) {
        CustomerPolicyRenewalDTO renewalFind = customerPolicyRenewalService.findById (renewalId).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", renewalId);
        model.addAttribute ("customerPolicyRenewalDTO", renewalFind);
        return "customer_policy_renewal/customer_policy_renewal_update";
    }


    @RequestMapping(value = "/update/{renewalId}/edit", method = RequestMethod.POST)
    public String updateCustomerPolicyRenewal (@PathVariable("renewalId") Long renewalId, @ModelAttribute("customerPolicyRenewalDTO") @Valid CustomerPolicyRenewalDTO customerPolicyRenewalDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerPolicyRenewalDTO);
        customerPolicyRenewalDTO.setRenewalId (renewalId);
        CustomerPolicyRenewalDTO updatedCustomerPolicyRenewal = customerPolicyRenewalService.update (customerPolicyRenewalDTO).join ();
        model.addAttribute ("customerPolicyRenewalDTO", updatedCustomerPolicyRenewal);
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerPolicyRenewalDTO") CustomerPolicyRenewalDTO customerPolicyRenewalDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerPolicyRenewalDTO);
        CustomerPolicyRenewalDTO updatedCustomerPolicyRenewal = customerPolicyRenewalService.update (customerPolicyRenewalDTO).join ();
        model.addAttribute ("customerPolicyRenewalDTO", updatedCustomerPolicyRenewal);
        return "redirect:/ui/customer-policy-renewal/get-all";
    }
}
