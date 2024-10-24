package intern.customer.agitoo.WebAPi.thymeleafUIControllers.AllinOne;

import intern.customer.agitoo.DTO.DTOs.*;
import intern.customer.agitoo.Service.Concretes.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/ui/company/")
public class CompanyAllinOneController {

    @Autowired
    private CompanyServiceImpl companyService;

    @Autowired
    private CompanyBranchServiceImpl companyBranchService;

    @Autowired
    private CompanyFinancialServiceImpl companyFinancialService;

    @Autowired
    private CustomerClaimServiceImpl customerClaimService;

    @Autowired
    private CustomerContactServiceImpl customerContactService;

    @Autowired
    private CustomerDebitCardServiceImpl customerDebitCardService;

    @Autowired
    private CustomerPaymentServiceImpl customerPaymentService;

    @Autowired
    private CustomerPolicyRenewalServiceImpl customerPolicyRenewalService;

    @Autowired
    private CustomerPolicyServiceImpl customerPolicyService;

    @Autowired
    private CustomerRegistrationServiceImpl customerRegistrationService;

    @RequestMapping(value = "/get-all/{companyId}/all-in-one", method = RequestMethod.GET)
    public String getAllInOne (@PathVariable("companyId") Long companyId, Model model) {

        CompanyDTO company = companyService.findById (companyId).join ();
        model.addAttribute ("company", company);
        CompanyBranchDTO branch = companyBranchService.findById (companyId).join ();
        model.addAttribute ("branch", branch);
        CompanyFinancialDTO financial = companyFinancialService.findById (companyId).join ();
        model.addAttribute ("financial", financial);

        CustomerClaimDTO customerClaim = customerClaimService.findById (companyId).join ();
        model.addAttribute ("claim", customerClaim);
        CustomerContactDTO customerContact = customerContactService.findById (companyId).join ();
        model.addAttribute ("contact", customerContact);
        CustomerDebitCardDTO customerDebitCard = customerDebitCardService.findById (companyId).join ();
        model.addAttribute ("card", customerDebitCard);
        CustomerPaymentDTO customerPayment = customerPaymentService.findById (companyId).join ();
        model.addAttribute ("payment", customerPayment);
        CustomerPolicyRenewalDTO customerPolicyRenewal = customerPolicyRenewalService.findById (companyId).join ();
        model.addAttribute ("renewal", customerPolicyRenewal);
        CustomerPolicyDTO customerPolicy = customerPolicyService.findById (companyId).join ();
        model.addAttribute ("policy", customerPolicy);
        CustomerRegistrationDTO customerRegistration = customerRegistrationService.findById (companyId).join ();
        model.addAttribute ("registration", customerRegistration);

        return "company_details";

    }


}
