package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Service.Concretes.PersonServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
