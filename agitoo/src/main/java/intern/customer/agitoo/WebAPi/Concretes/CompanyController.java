package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("/api/company")
//@Tag(name = "Company API", description = "Operations related to companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @RequestMapping (value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Operation(summary = "Get all company")
    public ResponseEntity<DataResult<List<CompanyDTO>>> getAll () {
        log.info ("Received request to list company branches!");
        List<CompanyDTO> companyDTOList = companyService.getAll ();

        DataResult<List<CompanyDTO>> response = new DataResult<>
                (companyDTOList,
                        true,
                        Messages.LISTED);
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyDTO>> Add (@RequestBody @Valid CompanyDTO companyDTO) {
        log.info ("Received request to update company {}", companyDTO);
        CompanyDTO savedCompany = companyService.add (companyDTO);

        DataResult<CompanyDTO> response = new DataResult<>
                (savedCompany, true, Messages.ADDED);
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyDTO>> Update (@RequestBody @Valid CompanyDTO companyDTO) {
        log.info ("Request receive to update company {}", companyDTO);
        CompanyDTO updatedCompany = companyService.update (companyDTO);
        DataResult<CompanyDTO> response = new DataResult<> (
                updatedCompany,
                true,
                Messages.UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete company branch  {}", id);
        this.companyService.deleteById (id);
    }
}