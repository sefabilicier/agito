package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.PersonFeedback;
import intern.customer.agitoo.Service.Abstracts.IPersonFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/personfeedback")
public class PersonFeedbackController {

    @Autowired
    private IPersonFeedbackService personFeedbackService;

    @GetMapping(value = "/getall")
    public DataResult<List<PersonFeedback>> getAll () {
        return this.personFeedbackService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (PersonFeedback personFeedback) {
        return this.personFeedbackService.Add (personFeedback);

    }

    @PutMapping(value = "/update")
    public Result Update (PersonFeedback personFeedback) {
        return this.personFeedbackService.Update (personFeedback);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.personFeedbackService.Delete (id);
    }
}
