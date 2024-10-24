package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyDTO;
import intern.customer.agitoo.Service.Abstracts.ICompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/company")
//@Tag(name = "Company API", description = "Operations related to companies")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    //@Operation(summary = "Get all company")
    public CompletableFuture<ResponseEntity<DataResult<List<CompanyDTO>>>> getAll () {
        log.info ("Received request to list company branches!");

        return companyService.getAll ()
                .thenApply (
                        companyDTOList ->
                        {
                            DataResult<List<CompanyDTO>> response = new DataResult<> (
                                    companyDTOList, true, LISTED);
                            return ResponseEntity.ok (response);
                        });
    }

    @RequestMapping(value = "/add",
            method = RequestMethod.POST,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CompanyDTO>>> Add (@RequestBody @Valid CompanyDTO companyDTO) {
        log.info ("Received request to update company {}", companyDTO);
        return companyService.add (companyDTO).thenApply (
                savedCompany -> {
                    DataResult<CompanyDTO> response = new DataResult<>
                            (savedCompany, true, ADDED);
                    return ResponseEntity.ok (response);
                }
        );


    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CompanyDTO>>> Update (@RequestBody @Valid CompanyDTO companyDTO) {
        log.info ("Request receive to update company {}", companyDTO);
        return companyService.update (companyDTO).thenApply (
                updatedCompany -> {
                    DataResult<CompanyDTO> response = new DataResult<> (
                            updatedCompany,
                            true,
                            UPDATED
                    );
                    return ResponseEntity.ok (response);
                }
        );

    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete company branch  {}", id);
        return companyService.deleteById (id)
                .thenApply (result ->
                {
                    DataResult<Void> response = new DataResult<> (
                            result,
                            true,
                            REMOVED);
                    return ResponseEntity.ok (response);
                });
    }
}