package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Models.enums.MaritalStatus;
import intern.customer.agitoo.Models.enums.Status;
import intern.customer.agitoo.Service.Concretes.PersonSupportTicketServiceImpl;
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
@RequestMapping("/ui/person-support-ticket/")
public class PersonSupportTicketUIController {

    @Autowired
    private PersonSupportTicketServiceImpl personSupportTicketService;

    @RequestMapping(value = "/get-all")
    public String getAllPage (Model model) {
        log.info ("HTML - Received request to list person support tickets!");
        List<PersonSupportTicketDTO> personSupportTicketDTOList = personSupportTicketService.getAll ().join ();
        model.addAttribute ("tickets", personSupportTicketDTOList);
        return "person_support_ticket/person_support_ticket_get_all";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String createPersonSupportTicket (@RequestParam(value = "personId", required = false) Long personId, Model model, PersonSupportTicketDTO personSupportTicketDTO) {
        log.info ("HTML - Received request to CREATE company {}", personSupportTicketDTO);
        model.addAttribute ("personSupportTicketDTO", personSupportTicketDTO);
        model.addAttribute ("status", Status.values ());

//        model.addAttribute ("personId", personSupportTicketDTO.getPerson ().getPersonId ()); //TODO : adding to the HTML
//        Person person = Person.builder ().personId (personId).build ();
//        personSupportTicketDTO.setPerson (person);

        personSupportTicketDTO.setPersonId (personId);

        return "person_support_ticket/person_support_ticket_add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String savePersonSupportTicket (@ModelAttribute("personSupportTicketDTO") @Valid PersonSupportTicketDTO personSupportTicketDTO, HttpSession httpSession) {
        log.info ("HTML - SAVED customer {}", personSupportTicketDTO);

        Person person = Person.builder ()
                .personId (personSupportTicketDTO.getPersonId ())
                .build ();
        personSupportTicketDTO.setPerson (person);

        PersonSupportTicketDTO savedPersonSupportTicket = personSupportTicketService.add (personSupportTicketDTO).join ();

//        Long createdPersonSupportTicketId = (Long) httpSession.getAttribute ("selectedPersonId");
//        Person person = Person.builder().personId (createdPersonSupportTicketId).build();
//        personSupportTicketDTO.setPerson (person);

        return "redirect:/ui/person-feedback/add?personId=" + personSupportTicketDTO.getPersonId ();
    }

    @RequestMapping(value = "/update/{ticketID}/edit", method = RequestMethod.GET)
    public String editPersonSupportTicket (@PathVariable("ticketID") Long ticketID, Model model) {
        PersonSupportTicketDTO ticketFind = personSupportTicketService.findById (ticketID).join ();
        log.info ("HTML - Received request to EDIT person with ID {}", ticketID);
        model.addAttribute ("personSupportTicketDTO", ticketFind);
        return "person_support_ticket/person_support_ticket_update";

    }

    @RequestMapping(value = "/update/{ticketID}/edit", method = RequestMethod.POST)
    public String updatePersonSupportTicket (@PathVariable("ticketID") Long ticketID,
                                             @ModelAttribute("personSupportTicketDTO") @Valid PersonSupportTicketDTO personSupportTicketDTO,
                                             Model model
    ) {
        log.info ("HTML - Received request to UPDATE person {}", personSupportTicketDTO);
        personSupportTicketDTO.setTicketID (ticketID);
        PersonSupportTicketDTO updatedPersonSupportTicket = personSupportTicketService.update (personSupportTicketDTO).join ();
        model.addAttribute ("status", MaritalStatus.values ());
        model.addAttribute ("personSupportTicketDTO", updatedPersonSupportTicket);
        return "redirect:/updating";
    }

    @RequestMapping(value = "/confirm-update", method = RequestMethod.POST)
    public String confirmUpdate (@ModelAttribute("personSupportTicketDTO") PersonSupportTicketDTO personSupportTicketDTO, Model model) {
        log.info ("HTML - Confirming update for person {}", personSupportTicketDTO);
        PersonSupportTicketDTO updatedPersonSupportTicket = personSupportTicketService.update (personSupportTicketDTO).join ();
        model.addAttribute ("personSupportTicketDTO", updatedPersonSupportTicket);
        return "redirect:/ui/person-support-ticket/get-all";
    }
}