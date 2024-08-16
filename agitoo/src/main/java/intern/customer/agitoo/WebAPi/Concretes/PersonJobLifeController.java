package java.intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.Models.Concretes.PersonJobLife;
import intern.customer.agitoo.Service.Abstracts.IPersonJobLifeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/personjoblife")
public class PersonJobLifeController {

    @Autowired
    private IPersonJobLifeService personJobLifeService;

    @GetMapping(value = "/getall")
    public DataResult<List<PersonJobLife>> getAll () {
        return this.personJobLifeService.getAll ();
    }

    @PostMapping(value = "/add")
    public Result Add (PersonJobLife personJobLife) {
        return this.personJobLifeService.Add (personJobLife);

    }

    @PutMapping(value = "/update")
    public Result Update (PersonJobLife personJobLife) {
        return this.personJobLifeService.Update (personJobLife);
    }

    @DeleteMapping(value = "/{id}")
    public Result Delete (@PathVariable Long id) {
        return this.personJobLifeService.Delete (id);
    }
}
