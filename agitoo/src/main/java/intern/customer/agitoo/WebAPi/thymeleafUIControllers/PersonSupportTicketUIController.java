package intern.customer.agitoo.WebAPi.thymeleafUIControllers;

import intern.customer.agitoo.DTO.DTOs.PersonDTO;
import intern.customer.agitoo.DTO.DTOs.PersonSupportTicketDTO;
import intern.customer.agitoo.Service.Concretes.PersonServiceImpl;
import intern.customer.agitoo.Service.Concretes.PersonSupportTicketServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/ui/person-support-ticket/")
public class PersonSupportTicketUIController {

    @Autowired
    private PersonSupportTicketServiceImpl personSupportTicketService;

    @RequestMapping( value = "/get-all")
    public String getAllPage(Model model) {
        log.info ("HTML - Received request to list person support tickets!");
        List<PersonSupportTicketDTO> personSupportTicketDTOList = personSupportTicketService.getAll();

        model.addAttribute("tickets", personSupportTicketDTOList);
        return "person_support_ticket/person_support_ticket_get_all";
    }

}
