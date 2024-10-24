package intern.customer.agitoo.WebAPi.uiPages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UpdatingPageController {

    @GetMapping(value = "updating")
    public String updating () {
        return "updating";
    }
}
