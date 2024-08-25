package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICompanyBranchService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/company-branch")
public class CompanyBranchController {

    @Autowired
    private ICompanyBranchService companyBranchService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CompanyBranchDTO>>> getAll () {
        log.info("Received request to list company branches!");
        List<CompanyBranchDTO> companyBranchDTOList = companyBranchService.getAll();

        DataResult<List<CompanyBranchDTO>> response = new DataResult<>
                (companyBranchDTOList, true, Messages.LISTED);
        return ResponseEntity.ok(response);

        /*return new ResponseEntity<List<CompanyBranchDTO>>
                        (companyBranchService.getAll (), HttpStatus.OK);*/
    }

    @RequestMapping(value = "/add",  method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CompanyBranchDTO>> Add (@RequestBody @Valid CompanyBranchDTO companyBranchDTO) {
        log.info("Received request to add company branch {}", companyBranchDTO);
        CompanyBranchDTO addedCompanyBranchDTO =
                companyBranchService.add (companyBranchDTO);

        DataResult<CompanyBranchDTO> response = new DataResult<>
                (addedCompanyBranchDTO, true, Messages.ADDED);
        return ResponseEntity.ok (response);

        //this.companyBranchService.add(companyBranchDTO);

    }

    @RequestMapping (value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyBranchDTO>> Update (@RequestBody @Valid CompanyBranchDTO companyBranchDTO) {
        log.info("Received request to update company branch {}", companyBranchDTO);
        CompanyBranchDTO updatedCompanyBranchDTO = companyBranchService.update (companyBranchDTO);
        DataResult<CompanyBranchDTO> response = new DataResult<> (
                updatedCompanyBranchDTO, true, Messages.UPDATED);

        return ResponseEntity.ok (response);

        //this.companyBranchService.update (companyBranch);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info("Received request to delete company branch {}", id);
        this.companyBranchService.deleteById (id);
    }
}