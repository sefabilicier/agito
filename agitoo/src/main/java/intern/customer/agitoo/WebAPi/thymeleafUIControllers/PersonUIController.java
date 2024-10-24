package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.Models.Concretes.Customer;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.PersonGender;
import intern.customer.agitoo.Service.Concretes.PersonServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person/")
public class PersonUIController {

    @Autowired
    private PersonServiceImpl personService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list person!");
        List<PersonDTO> personDTOList = personService.getAll ().join ();
        model.addAttribute ("personList", personDTOList);
        return "person/person_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPerson (
            @RequestParam(value = "customerId", required = false) Long customerId,
            Model model,
            PersonDTO personDTO) {
        log.info ("HTML - Received request to CREATE person {}", personDTO);

        model.addAttribute ("gender", PersonGender.values ());
        model.addAttribute ("status", MaritalStatus.values ());
        model.addAttribute ("personDTO", personDTO);

        personDTO.setCustomerId (customerId);

        return "person/person_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePerson (@ModelAttribute("personDTO") @Valid PersonDTO personDTO,
                              RedirectAttributes redirectAttributes
    ) {
        log.info ("HTML - SAVED person {}", personDTO);

        Customer customer = Customer.builder ()
                .customerId (personDTO.getCustomerId ())
                .build ();
        personDTO.setCustomer (customer);

        PersonDTO savedPersonId = personService.add (personDTO).join ();
        redirectAttributes.addFlashAttribute ("personId", savedPersonId.getPersonId ());

        //session tarafında tutulan ID nin burada kullanımı
//        Long createdPersonId = (Long) httpSession.getAttribute ("selectedCustomerId");
//        Customer customer = Customer.builder ().customerId (createdPersonId).build ();
//        personDTO.setCustomer (customer);

        return "redirect:/ui/person-job-life/add?personId=" + savedPersonId.getPersonId ();
    }

    @RequestMapping(value = "/update/{personId}/edit", method = RequestMethod.GET)
    public String editPerson (@PathVariable("personId") Long personId, Model model) {
        PersonDTO personFind = personService.findById (personId).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", personId);
        model.addAttribute ("personDTO", personFind);
        model.addAttribute ("gender", PersonGender.values ());
        model.addAttribute ("status", MaritalStatus.values ());
        return "person/person_update";

    }

    @RequestMapping(value = "/update/{personId}/edit", method = RequestMethod.POST)
    public String updatePerson (@PathVariable("personId") Long personId, @ModelAttribute("personDTO") @Valid PersonDTO personDTO, Model model) {
        log.info ("HTML - Received request to UPDATE person {}", personDTO);
        personDTO.setPersonId (personId);
        PersonDTO updatedPerson = personService.update (personDTO).join ();
        model.addAttribute ("personDTO", updatedPerson);
        return "redirect:/updating";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personDTO") PersonDTO personDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", personDTO);
        PersonDTO updatedPerson = personService.update (personDTO).join ();
        model.addAttribute ("personDTO", updatedPerson);
        return "redirect:/ui/person/get-all";
    }

    @RequestMapping(value = "/delete/{personId}/delete", method = RequestMethod.DELETE)
    public String deletePerson (@PathVariable("personId") Long personId) {
        log.info ("HTML - Received request to delete for person {}", personId);
        personService.deleteById (personId);
        return "redirect:/deleting";
    }

    @RequestMapping(value = "/confirm-delete", method = RequestMethod.POST)
    public String confirmUpdate (Long personId) {
        log.info ("HTML - Confirming delete for person {}", personId);
        personService.deleteById (personId);
        return "redirect:/ui/person/get-all";
    }
}