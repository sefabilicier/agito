package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CustomerDTO;
import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Service.Concretes.CustomerServiceImpl;
import intern.customer.agitoo.Service.Concretes.PersonActivityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person-activity/")
public class PersonActivityUIController {

    @Autowired
    private PersonActivityServiceImpl personActivityService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list person activities!");
        List<PersonActivityDTO> personActivityDTOList = personActivityService.getAll();
        model.addAttribute("activities", personActivityDTOList);
        return "person_activity/person_acitivity_get_all";
    }
}