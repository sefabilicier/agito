package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.EmploymentType;
import intern.customer.agitoo.Service.Concretes.PersonJobLifeServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person-job-life/")
public class PersonJobLifeUIController {

    @Autowired
    private PersonJobLifeServiceImpl personJobLifeService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list person job lives!");
        List<PersonJobLifeDTO> personJobLifeDTOList = personJobLifeService.getAll ().join ();
        model.addAttribute ("jobs", personJobLifeDTOList);
        return "person_job_life/person_job_life_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPersonJobLife (@RequestParam(value = "personId", required = false) Long personId, Model model, PersonJobLifeDTO personJobLifeDTO) {
        log.info ("HTML - Received request to CREATE company {}", personJobLifeDTO);
        model.addAttribute ("personJobLifeDTO", personJobLifeDTO);
        model.addAttribute ("employmentType", EmploymentType.values ());

//        model.addAttribute ("personId", personJobLifeDTO.getPerson ().getPersonId ()); //TODO : adding to the HTML
//        Person person = Person.builder ().personId (personId).build ();
//        personJobLifeDTO.setPerson (person);
        personJobLifeDTO.setPersonId (personId);

        return "person_job_life/person_job_life_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePersonJobLife (@ModelAttribute("personJobLifeDTO") @Valid PersonJobLifeDTO personJobLifeDTO) {
        log.info ("HTML - SAVED customer {}", personJobLifeDTO);


        Person person = Person.builder ()
                .personId (personJobLifeDTO.getPersonId ())
                .build ();
        personJobLifeDTO.setPerson (person);

        PersonJobLifeDTO savedPersonJobLife = personJobLifeService.add (personJobLifeDTO).join ();

//        Long createdPersonJobLifeId = (Long) httpSession.getAttribute ("selectedPersonId");
//        Person person = Person.builder ().personId (createdPersonJobLifeId).build ();
//        personJobLifeDTO.setPerson (person);

        return "redirect:/ui/person-support-ticket/add?personId" + savedPersonJobLife.getPersonId ();
    }

    @RequestMapping(value = "/update/{jobID}/edit", method = RequestMethod.GET)
    public String editPersonJobLife (@PathVariable("jobID") Long jobID, Model model) {
        PersonJobLifeDTO jobLifeFind = personJobLifeService.findById (jobID).join ();
        log.info ("HTML - Received request to EDIT person feedback with ID {}", jobID);
        model.addAttribute ("personJobLifeDTO", jobLifeFind);
        return "person_job_life/person_job_life_update";
    }

    @RequestMapping(value = "/update/{jobID}/edit", method = RequestMethod.POST)
    public String updatePersonJobLife (@PathVariable("jobID") Long jobID, @ModelAttribute("personJobLifeDTO") @Valid PersonJobLifeDTO personJobLifeDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", personJobLifeDTO);
        personJobLifeDTO.setJobID (jobID);
        PersonJobLifeDTO personJobLifeUpdated = personJobLifeService.update (personJobLifeDTO).join ();
        model.addAttribute ("personJobLifeDTO", personJobLifeUpdated);
        model.addAttribute ("employmentType", EmploymentType.values ());
        return "redirect:/updating";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personJobLifeDTO") PersonJobLifeDTO personJobLifeDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", personJobLifeDTO);
        PersonJobLifeDTO personJobLifeUpdated = personJobLifeService.update (personJobLifeDTO).join ();
        model.addAttribute ("personJobLifeDTO", personJobLifeUpdated);
        return "redirect:/ui/person-job-life/get-all";
    }
}