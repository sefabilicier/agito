package intern.customer.agitoo.WebAPi.SecurityTemplate;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

    @GetMapping(value = "login")
    public String getLogin () {
        return "login";
    }

    @GetMapping(value = "logout")
    public String logOut () {
        return "logout";
    }

    @GetMapping(value = "login-expired")
    public String loginExpired () {
        return "login_expired";
    }

}