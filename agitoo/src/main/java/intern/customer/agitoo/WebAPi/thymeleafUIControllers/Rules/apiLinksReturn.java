package intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class apiLinksReturn {

    public Map<String, String> uiApiLinks () {
        Map<String, String> apiLinks = new HashMap<> ();
        apiLinks.put ("Customer", "/ui/customer/get-all");
        apiLinks.put ("Person", "/ui/person/get-all");
        apiLinks.put ("Person Activity", "/ui/person-activity/get-all");
        apiLinks.put ("Person Feedback", "/ui/person-feedback/get-all");
        apiLinks.put ("Person Job Life", "/ui/person-job-life/get-all");
        apiLinks.put ("Person Support Ticket", "/ui/person-support-ticket/get-all");
        apiLinks.put ("Company", "/ui/company/get-all");
        apiLinks.put ("Company Branch", "/ui/company-branch/get-all");
        apiLinks.put ("Company Financial", "/ui/company-financial/get-all");
        apiLinks.put ("Customer Address", "/ui/customer-address/get-all");
        apiLinks.put ("Customer Address Country", "/ui/customer-address-country/get-all");
        apiLinks.put ("Customer Address City", "/ui/customer-address-city/get-all");
        apiLinks.put ("Customer Claim", "/ui/customer-claim/get-all");
        apiLinks.put ("Customer Contact", "/ui/customer-contact/get-all");
        apiLinks.put ("Customer Debit Card", "/ui/customer-debit-card/get-all");
        apiLinks.put ("Customer Payment", "/ui/customer-payment/get-all");
        apiLinks.put ("Customer Policy", "/ui/customer-policy/get-all");
        apiLinks.put ("Customer Policy Renewal", "/ui/customer-policy-renewal/get-all");
        apiLinks.put ("Customer Registration", "/ui/customer-registration/get-all");

        //apiLinks.forEach(System.out::println);

        return apiLinks;
    }

}
