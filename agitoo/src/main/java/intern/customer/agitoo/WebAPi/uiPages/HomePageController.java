package intern.customer.agitoo.WebAPi.uiPages;

import intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules.countCustomer;
import intern.customer.agitoo.WebAPi.thymeleafUIControllers.Rules.apiLinksReturn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class HomePageController {

    @Autowired
    private countCustomer countCustomer;

    @Autowired
    private apiLinksReturn apiLinksReturn;

    @GetMapping(value = "home")
    public String getHome(Model model) {
        model.addAttribute ("apiLinks", apiLinksReturn.uiApiLinks ());
        model.addAttribute ("customerCount", countCustomer.countCompanyType());
        return "home";
    }

}
