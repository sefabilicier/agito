package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.Person;
import intern.customer.agitoo.Service.Abstracts.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @GetMapping(value = "/getall")
    public DataResult<List<Person>> getAll () {
        return this.personService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (Person person) {
        return this.personService.Add (person);

    }

    @PutMapping(value = "/update")
    public Result Update (Person person) {
        return this.personService.Update (person);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.personService.Delete (id);
    }
}
