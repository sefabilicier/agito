package intern.customer.agitoo.WebAPi.thymeleafUIControllers;


import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.DTOs.PersonFeedbackDTO;
import intern.customer.agitoo.Service.Concretes.PersonFeedbackServiceImpl;
import intern.customer.agitoo.Service.Concretes.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person-feedback/")
public class PersonFeedbackUIController {

    @Autowired
    private PersonFeedbackServiceImpl personFeedbackService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list person feedbacks!");
        List<PersonFeedbackDTO> personFeedbackDTOList = personFeedbackService.getAll();

        model.addAttribute("feedbacks", personFeedbackDTOList);
        return "person_feedback/person_feedback_get_all";
    }

}
