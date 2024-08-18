package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Core.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("/api/company-branch")
public class CompanyBranchController {

    @Autowired
    private ICompanyBranchService companyBranchService;

    @RequestMapping(value = "/getall", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CompanyBranchDTO>>> getAll () {
        log.info("Received request to list company branches!");
        List<CompanyBranchDTO> CompanyBranchDTOList = companyBranchService.getAll ();
        DataResult<List<CompanyBranchDTO>> response = new DataResult<>
                (CompanyBranchDTOList, true, "Customer branches listed!");
        return ResponseEntity.ok(response);

        /*return new ResponseEntity<List<CompanyBranchDTO>>
                        (companyBranchService.getAll (), HttpStatus.OK);*/
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CompanyBranchDTO>> Add (@RequestBody CompanyBranchDTO companyBranchDTO) {
        log.info("Received request to add company branch {}", companyBranchDTO);
        CompanyBranchDTO addedCompanyBranchDTO = companyBranchService.add (companyBranchDTO);

        DataResult<CompanyBranchDTO> response = new DataResult<>
                (addedCompanyBranchDTO, true, "Customer branch added!");
        return ResponseEntity.ok (response);

        //this.companyBranchService.add(companyBranchDTO);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyBranchDTO>> Update (@RequestBody CompanyBranchDTO companyBranchDTO) {
        log.info("Received request to update company branch {}", companyBranchDTO);
        CompanyBranchDTO updatedCompanyBranchDTO = companyBranchService.update (companyBranchDTO);
        DataResult<CompanyBranchDTO> response = new DataResult<> (
                updatedCompanyBranchDTO, true, "Customer branch updated");

        return ResponseEntity.ok (response);

        //this.companyBranchService.update (companyBranch);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable Long id) {
        log.info("Received request to delete company branch {}", id);
        this.companyBranchService.deleteById (id);
    }
}