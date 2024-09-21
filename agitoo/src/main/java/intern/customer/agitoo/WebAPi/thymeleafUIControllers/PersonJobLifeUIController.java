package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.DTOs.PersonJobLifeDTO;
import intern.customer.agitoo.Models.enums.EmploymentType;
import intern.customer.agitoo.Service.Concretes.PersonJobLifeServiceImpl;
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
@RequestMapping("/ui/person-job-life/")
public class PersonJobLifeUIController {

    @Autowired
    private PersonJobLifeServiceImpl personJobLifeService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list person job lives!");
        List<PersonJobLifeDTO> personJobLifeDTOList = personJobLifeService.getAll();

        model.addAttribute("jobs", personJobLifeDTOList);
        return "person_job_life/person_job_life_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createCompany(Model model, PersonJobLifeDTO personJobLifeDTO){
        log.info ("HTML - Received request to CREATE company {}", personJobLifeDTO);
        model.addAttribute ("personJobLifeDTO", personJobLifeDTO);
        model.addAttribute ("employmentType", EmploymentType.values ());
        return "person_job_life/person_job_life_add";
    }

    @RequestMapping(value ="/add", method = RequestMethod.POST)
    public String saveCustomer(@ModelAttribute("personJobLifeDTO") PersonJobLifeDTO personJobLifeDTO){
        log.info ("HTML - SAVED customer {}", personJobLifeDTO);
        personJobLifeService.add (personJobLifeDTO);

        return "redirect:/ui/customer-contact/add";
    }

}