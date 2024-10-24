package intern.customer.agitoo.WebAPi.uiPages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class DetailPageController {

    @GetMapping(value = "person-detail")
    public String personOne () {
        return "person_details";
    }

    @GetMapping(value = "company-detail")
    public String companyOne () {
        return "company_details";
    }

}
