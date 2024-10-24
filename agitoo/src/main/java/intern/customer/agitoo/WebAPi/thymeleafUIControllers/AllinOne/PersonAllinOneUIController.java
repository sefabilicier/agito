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
@RequestMapping("/ui/person/")
public class PersonAllinOneUIController {

    @Autowired
    private PersonServiceImpl personService;

    @Autowired
    private PersonSupportTicketServiceImpl personSupportTicketService;

    @Autowired
    private PersonJobLifeServiceImpl personJobLifeService;

    @Autowired
    private PersonFeedbackServiceImpl personFeedbackService;

    @Autowired
    private PersonActivityServiceImpl personActivityService;

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

    @RequestMapping(value = "/get-all/{personId}/all-in-one", method = RequestMethod.GET)
    public String getAllInOne (@PathVariable("personId") Long personId, Model model) {
        PersonDTO person = personService.findById (personId).join ();
        model.addAttribute ("person", person);
        PersonSupportTicketDTO personSupportTicket = personSupportTicketService.findById (personId).join ();
        model.addAttribute ("ticket", personSupportTicket);
        PersonJobLifeDTO personJobLife = personJobLifeService.findById (personId).join ();
        model.addAttribute ("job", personJobLife);
        PersonFeedbackDTO personFeedback = personFeedbackService.findById (personId).join ();
        model.addAttribute ("feedback", personFeedback);
        PersonActivityDTO personActivity = personActivityService.findById (personId).join ();
        model.addAttribute ("activity", personActivity);

        CustomerClaimDTO customerClaim = customerClaimService.findById (personId).join ();
        model.addAttribute ("claim", customerClaim);
        CustomerContactDTO customerContact = customerContactService.findById (personId).join ();
        model.addAttribute ("contact", customerContact);
        CustomerDebitCardDTO customerDebitCard = customerDebitCardService.findById (personId).join ();
        model.addAttribute ("card", customerDebitCard);
        CustomerPaymentDTO customerPayment = customerPaymentService.findById (personId).join ();
        model.addAttribute ("payment", customerPayment);
        CustomerPolicyRenewalDTO customerPolicyRenewal = customerPolicyRenewalService.findById (personId).join ();
        model.addAttribute ("renewal", customerPolicyRenewal);
        CustomerPolicyDTO customerPolicy = customerPolicyService.findById (personId).join ();
        model.addAttribute ("policy", customerPolicy);
        CustomerRegistrationDTO customerRegistration = customerRegistrationService.findById (personId).join ();
        model.addAttribute ("registration", customerRegistration);

        return "person_details";

    }


}
