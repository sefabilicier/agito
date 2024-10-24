package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerClaimDTO;
import intern.customer.agitoo.Models.Concretes.CustomerPolicy;
import intern.customer.agitoo.Models.enums.ClaimStatus;
import intern.customer.agitoo.Service.Concretes.CustomerClaimServiceImpl;
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
@RequestMapping("/ui/customer-claim/")
public class CustomerClaimUIController {

    @Autowired
    private CustomerClaimServiceImpl customerClaimService;

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
        List<CustomerClaimDTO> customerClaimDTOList = customerClaimService.getAll ().join ();
        model.addAttribute ("claims", customerClaimDTOList);
        return "customer_claim/customer_claim_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCustomerClaim (
            @RequestParam(value = "customerPolicyId", required = false) Long customerPolicyId,
            Model model,
            CustomerClaimDTO customerClaimDTO
    ) {

        log.info ("HTML - Received request to CREATE company {}", customerClaimDTO);
        model.addAttribute ("customerClaimDTO", customerClaimDTO);
        model.addAttribute ("claimStatus", ClaimStatus.values ());

        customerClaimDTO.setCustomerPolicyId (customerPolicyId);

//        model.addAttribute ("customerPolicyId", customerClaimDTO.getCustomerPolicy ().getCustomerPolicyId ()); //TODO : adding to HTML
//        CustomerPolicy customerPolicy = CustomerPolicy.builder().customerPolicyId (customerPolicyId).build();
//        customerClaimDTO.setCustomerPolicy (customerPolicy);

        return "customer_claim/customer_claim_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String saveCustomerClaim (@ModelAttribute("customerClaimDTO") @Valid CustomerClaimDTO customerClaimDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", customerClaimDTO);

        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (
                        customerClaimDTO.getCustomerPolicyId ())
                .build ();
        customerClaimDTO.setCustomerPolicy (customerPolicy);

        CustomerClaimDTO savedCustomerClaim = customerClaimService.add (customerClaimDTO).join ();

//        Long createdCustomerClaimId = (Long) httpSession.getAttribute ("selectedPolicyId");
//        CustomerPolicy customerPolicy = CustomerPolicy.builder ().customerPolicyId (createdCustomerClaimId).build ();
//        customerClaimDTO.setCustomerPolicy (customerPolicy);

        return "redirect:/redirectingHome"; //TODO : CUSTOMER RECAP PAGE - SHOWS THE SUM OF HIS/HER
    }

    @RequestMapping(value = "/update/{claimId}/edit", method = RequestMethod.GET)
    public String editCustomerClaim (@PathVariable("claimId") Long claimId, Model model) {
        CustomerClaimDTO claimFind = customerClaimService.findById (claimId).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", claimId);
        model.addAttribute ("customerClaimDTO", claimFind);
        return "customer_claim/customer_claim_update";

    }

    @RequestMapping(value = "/update/{claimId}/edit", method = RequestMethod.POST)
    public String updateCustomerClaim (@PathVariable("claimId") Long claimId, @ModelAttribute("customerClaimDTO") @Valid CustomerClaimDTO customerClaimDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", customerClaimDTO);
        customerClaimDTO.setClaimId (claimId);
        CustomerClaimDTO updatedCustomerClaim = customerClaimService.update (customerClaimDTO).join ();
        model.addAttribute ("customerClaimDTO", updatedCustomerClaim);
        return "redirect:/updating";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("customerClaimDTO") CustomerClaimDTO customerClaimDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", customerClaimDTO);
        CustomerClaimDTO updatedCustomerClaim = customerClaimService.update (customerClaimDTO).join ();
        model.addAttribute ("customerClaimDTO", updatedCustomerClaim);
        return "redirect:/ui/customer-claim/get-all";
    }
}
