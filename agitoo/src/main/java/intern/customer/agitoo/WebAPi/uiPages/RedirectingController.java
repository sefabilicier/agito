package intern.customer.agitoo.WebAPi.uiPages;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/")
public class RedirectingController {

    @GetMapping(value = "/redirectingHome")
    public String redirectionHome () {
        return "redirectionHome";
    }
}
