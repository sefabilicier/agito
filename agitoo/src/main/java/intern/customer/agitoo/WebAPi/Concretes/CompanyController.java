package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.Core.Results.Result;
import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.Models.Concretes.Company;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.crypto.Data;
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
                        "Companies listed!");
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyDTO>> Add (@RequestBody CompanyDTO companyDTO) {
        log.info ("Received request to update company {}", companyDTO);
        CompanyDTO savedCompany = companyService.add (companyDTO);

        DataResult<CompanyDTO> response = new DataResult<>
                (savedCompany, true, "Company added!");
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyDTO>> Update (@RequestBody CompanyDTO companyDTO) {
        log.info ("Request receive to update company {}", companyDTO);
        CompanyDTO updatedCompany = companyService.update (companyDTO);
        DataResult<CompanyDTO> response = new DataResult<> (
                updatedCompany,
                true,
                "Received request to update company!"
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public void Delete (@PathVariable Long id) {
        log.info ("Received request to delete company branch  {}", id);
        this.companyService.deleteById (id);
    }
}