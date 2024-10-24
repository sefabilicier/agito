package intern.customer.agitoo.WebAPi.thymeleafUIControllers;


import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.FeedbackType;
import intern.customer.agitoo.Service.Concretes.PersonFeedbackServiceImpl;
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
@RequestMapping("/ui/person-feedback/")
public class PersonFeedbackUIController {

    @Autowired
    private PersonFeedbackServiceImpl personFeedbackService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list person feedbacks!");
        List<PersonFeedbackDTO> personFeedbackDTOList = personFeedbackService.getAll ().join ();
        model.addAttribute ("feedbacks", personFeedbackDTOList);
        return "person_feedback/person_feedback_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPersonFeedback (@RequestParam(value = "personId", required = false) Long personId, Model model, PersonFeedbackDTO personFeedbackDTO) {
        log.info ("HTML - Received request to CREATE company {}", personFeedbackDTO);
        model.addAttribute ("personFeedbackDTO", personFeedbackDTO);
        model.addAttribute ("feedbackType", FeedbackType.values ());

//        model.addAttribute ("personId", personFeedbackDTO.getPerson ().getPersonId ()); //TODO : adding to the HTML
//        Person person = Person.builder ().personId (personId).build ();
//        personFeedbackDTO.setPerson (person);

        personFeedbackDTO.setPersonId (personId);

        return "person_feedback/person_feedback_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePersonFeedback (@ModelAttribute("personFeedbackDTO") @Valid PersonFeedbackDTO personFeedbackDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", personFeedbackDTO);

        Person person = Person.builder ()
                .personId (personFeedbackDTO.getPersonId ())
                .build ();
        personFeedbackDTO.setPerson (person);

        PersonFeedbackDTO savedPersonFeedback = personFeedbackService.add (personFeedbackDTO).join ();

//        Long createdPersonFeedbackId = (Long) httpSession.getAttribute ("selectedPersonId");
//        Person person = Person.builder ().personId (createdPersonFeedbackId).build ();
//        personFeedbackDTO.setPerson (person);

        return "redirect:/ui/person-activity/add?personId=" + personFeedbackDTO.getPersonId ();
    }

    @RequestMapping(value = "/update/{feedbackId}/edit", method = RequestMethod.GET)
    public String editPersonFeedback (@PathVariable("feedbackId") Long feedbackId, Model model) {
        PersonFeedbackDTO feedbackFind = personFeedbackService.findById (feedbackId).join ();
        log.info ("HTML - Received request to EDIT person feedback with ID {}", feedbackId);
        model.addAttribute ("personFeedbackDTO", feedbackFind);
        return "person_feedback/person_feedback_update";
    }


    @RequestMapping(value = "/update/{feedbackId}/edit", method = RequestMethod.POST)
    public String updatePersonFeedback (@PathVariable("feedbackId") Long feedbackId,
                                        @ModelAttribute("personFeedbackDTO") @Valid PersonFeedbackDTO personFeedbackDTO,
                                        Model model) {
        log.info ("HTML - Received request to UPDATE person {}", personFeedbackDTO);
        personFeedbackDTO.setFeedbackId (feedbackId);
        PersonFeedbackDTO personFeedbackUpdated = personFeedbackService.update (personFeedbackDTO).join ();
        model.addAttribute ("personFeedbackDTO", personFeedbackUpdated);
        model.addAttribute ("feedbackType", FeedbackType.values ());
        return "redirect:/updating";
    }


    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personFeedbackDTO") PersonFeedbackDTO personFeedbackDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", personFeedbackDTO);
        PersonFeedbackDTO personFeedbackUpdated = personFeedbackService.update (personFeedbackDTO).join ();
        model.addAttribute ("personFeedbackDTO", personFeedbackUpdated);
        return "redirect:/ui/person-feedback/get-all";
    }

}
