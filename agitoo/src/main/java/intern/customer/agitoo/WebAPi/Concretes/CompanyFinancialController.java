package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyFinancialDTO;
import intern.customer.agitoo.Helper.Messages;
import intern.customer.agitoo.Service.Abstracts.ICompanyFinancialService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/company-financial")
public class CompanyFinancialController {

    @Autowired
    private ICompanyFinancialService companyFinancialService;


    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<List<CompanyFinancialDTO>>> getAll () {
        log.info ("Received request to list company branches!");
        List<CompanyFinancialDTO> companyFinancialDTOList = companyFinancialService.getAll ();
        DataResult<List<CompanyFinancialDTO>> response = new DataResult<> (
                companyFinancialDTOList,
                true,
                LISTED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<DataResult<CompanyFinancialDTO>> Add (@RequestBody @Valid CompanyFinancialDTO companyFinancialsDTO) {
        log.info ("Received request to add company branch {}", companyFinancialsDTO);
        CompanyFinancialDTO savedCompanyFinancial = companyFinancialService.add (companyFinancialsDTO);
        DataResult<CompanyFinancialDTO> response = new DataResult<> (savedCompanyFinancial, true, ADDED);
        return ResponseEntity.ok (response);

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DataResult<CompanyFinancialDTO>> Update (@RequestBody @Valid CompanyFinancialDTO companyFinancialsDTO) {
        log.info ("Received request to update company branch {}", companyFinancialsDTO);
        CompanyFinancialDTO updatedCompanyFinancial = companyFinancialService.update (companyFinancialsDTO);
        DataResult<CompanyFinancialDTO> response = new DataResult<> (
                updatedCompanyFinancial,
                true,
                UPDATED
        );
        return ResponseEntity.ok (response);
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public void Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete company branch {}", id);
        this.companyFinancialService.deleteById (id);
    }
}
