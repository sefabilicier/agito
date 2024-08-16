package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.PersonActivity;
import intern.customer.agitoo.Service.Abstracts.IPersonActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/personactivity")
public class PersonActivityController {

    @Autowired
    private IPersonActivityService personActivityService;

    @GetMapping(value = "/getall")
    public DataResult<List<PersonActivity>> getAll () {
        return this.personActivityService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (PersonActivity personActivity) {
        return this.personActivityService.Add (personActivity);

    }

    @PutMapping(value = "/update")
    public Result Update (PersonActivity personActivity) {
        return this.personActivityService.Update (personActivity);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.personActivityService.Delete (id);
    }
}
