package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.PersonSupportTicket;
import intern.customer.agitoo.Service.Abstracts.IPersonSupportTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/personsupportticket")
public class PersonSupportTicketController {

    @Autowired
    private IPersonSupportTicketService personSupportTicketService;

    @GetMapping(value = "/getall")
    public DataResult<List<PersonSupportTicket>> getAll () {
        return this.personSupportTicketService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (PersonSupportTicket personSupportTicket) {
        return this.personSupportTicketService.Add (personSupportTicket);

    }

    @PutMapping(value = "/update")
    public Result Update (PersonSupportTicket personSupportTicket) {
        return this.personSupportTicketService.Update (personSupportTicket);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.personSupportTicketService.Delete (id);
    }
}
