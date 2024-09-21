package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.CustomerType;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import intern.customer.agitoo.Service.Concretes.PersonServiceImpl;
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
@RequestMapping("/ui/person/")
public class PersonUIController {

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list person!");
        List<PersonDTO> personDTOList = personService.getAll();

        model.addAttribute("personList", personDTOList);
        return "person/person_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPerson(Model model, PersonDTO personDTO, Person person){
        log.info ("HTML - Received request to CREATE person {}", personDTO);
        model.addAttribute ("gender", PersonGender.values ());
        model.addAttribute ("status", MaritalStatus.values ());
        model.addAttribute ("personDTO", personDTO);
        return "person/person_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String savePerson(@ModelAttribute("personDTO") PersonDTO personDTO){
        log.info ("HTML - SAVED person {}", personDTO);
        personService.add (personDTO);
        return "redirect:/ui/person-job-life/add";
    }

}
