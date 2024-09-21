package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Models.enums.ActivityType;
import intern.customer.agitoo.Service.Concretes.PersonActivityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        return "person_activity/person_activity_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, PersonActivityDTO personActivityDTO){
        log.info ("HTML - Received request to CREATE company {}", personActivityDTO);
        model.addAttribute ("personActivityDTO", personActivityDTO);
        model.addAttribute ("activityType", ActivityType.values ());
        return "person_activity/person_activity_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("personActivityDTO") PersonActivityDTO personActivityDTO){
        log.info ("HTML - SAVED customer {}", personActivityDTO);
        personActivityService.add (personActivityDTO);

        return "redirect:/templates/home";
    }
}