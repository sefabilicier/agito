package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonActivityDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.ActivityType;
import intern.customer.agitoo.Service.Concretes.PersonActivityServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person-activity/")
public class PersonActivityUIController {

    @Autowired
    private PersonActivityServiceImpl personActivityService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list person activities!");
        List<PersonActivityDTO> personActivityDTOList = personActivityService.getAll ().join ();
        model.addAttribute ("activities", personActivityDTOList);
        return "person_activity/person_activity_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPersonActivity (@RequestParam(value = "personId", required = false) Long personId, Model model, PersonActivityDTO personActivityDTO) {
        log.info ("HTML - Received request to CREATE company {}", personActivityDTO);
        model.addAttribute ("personActivityDTO", personActivityDTO);
        model.addAttribute ("activityType", ActivityType.values ());

        personActivityDTO.setPersonId (personId);

//        model.addAttribute ("personId", personActivityDTO.getPerson ().getPersonId ()); //TODO : adding to the HTML
//        Person person = Person.builder ().personId (personId).build ();
//        personActivityDTO.setPerson (person);

        return "person_activity/person_activity_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePersonActivity (@ModelAttribute("personActivityDTO") @Valid PersonActivityDTO personActivityDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", personActivityDTO);

        Person person = Person.builder ()
                .personId (personActivityDTO.getPersonId ())
                .build ();
        personActivityDTO.setPerson (person);

        PersonActivityDTO savedPersonActivity = personActivityService.add (personActivityDTO).join ();

//        Long createdPersonActivityId = (Long) httpSession.getAttribute ("selectedPersonId");
//        Person person = Person.builder ().personId (createdPersonActivityId).build ();
//        personActivityDTO.setPerson (person);


        return "redirect:/ui/customer-contact/add?customerId=" + savedPersonActivity.getPerson ().getCustomer ().getCustomerId ();
    }

    @RequestMapping(value = "/update/{activityId}/edit", method = RequestMethod.GET)
    public String editPersonActivity (@PathVariable("activityId") Long activityId, Model model) {
        PersonActivityDTO activityFind = personActivityService.findById (activityId).join ();
        log.info ("HTML - Received request to EDIT person activity with ID {}", activityId);
        model.addAttribute ("personActivityDTO", activityFind);
        return "person_activity/person_activity_update";

    }

    @RequestMapping(value = "/update/{activityId}/edit", method = RequestMethod.POST)
    public String updatePersonActivity (
            @PathVariable("activityId") Long activityId,
            @ModelAttribute("personActivityDTO") @Valid PersonActivityDTO personActivityDTO,
            Model model
    ) {
        log.info ("HTML - Received request to UPDATE person {}", personActivityDTO);
        personActivityDTO.setActivityId (activityId);
        PersonActivityDTO personActivityUpdated = personActivityService.update (personActivityDTO).join ();
        model.addAttribute ("personActivityDTO", personActivityUpdated);
        model.addAttribute ("activityType", ActivityType.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personActivityDTO") PersonActivityDTO personActivityDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", personActivityDTO);
        PersonActivityDTO personActivityUpdated = personActivityService.update (personActivityDTO).join ();
        model.addAttribute ("personActivityDTO", personActivityUpdated);
        return "redirect:/ui/person-activity/get-all";
    }
}