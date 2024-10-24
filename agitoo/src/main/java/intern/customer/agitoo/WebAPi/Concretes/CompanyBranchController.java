package intern.customer.agitoo.WebAPi.Concretes;

import intern.customer.agitoo.Common.Results.DataResult;
import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
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
import java.util.concurrent.CompletableFuture;

import static intern.customer.agitoo.Helper.Messages.*;

@Slf4j
@RestController
@RequestMapping("/api/company-branch")
public class CompanyBranchController {

    @Autowired
    private ICompanyBranchService companyBranchService;

    @RequestMapping(value = "/get-all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<List<CompanyBranchDTO>>>> getAll () {
        log.info ("Received request to list company branches!");

        return companyBranchService.getAll ().
                thenApply (
                        CompanyBranchDTOList -> {
                            DataResult<List<CompanyBranchDTO>> response = new DataResult<>
                                    (CompanyBranchDTOList, true, LISTED);
                            return ResponseEntity.ok (response);
                        });

    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public CompletableFuture<ResponseEntity<DataResult<CompanyBranchDTO>>> Add (@RequestBody @Valid CompanyBranchDTO companyBranchDTO) {
        log.info ("Received request to add company branch {}", companyBranchDTO);

        return companyBranchService.add (companyBranchDTO)
                .thenApply (
                        addedCompanyBranchDTO -> {
                            DataResult<CompanyBranchDTO> response = new DataResult<>
                                    (addedCompanyBranchDTO, true, ADDED);
                            return ResponseEntity.ok (response);
                        });

    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public CompletableFuture<ResponseEntity<DataResult<CompanyBranchDTO>>> Update (@RequestBody @Valid CompanyBranchDTO companyBranchDTO) {
        log.info ("Received request to update company branch {}", companyBranchDTO);

        return companyBranchService.update (companyBranchDTO).thenApply (
                updatedCompanyBranchDTO -> {
                    DataResult<CompanyBranchDTO> response = new DataResult<> (
                            updatedCompanyBranchDTO, true, UPDATED);
                    return ResponseEntity.ok (response);
                });
    }

    @RequestMapping(value = "/delete-by-id/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<ResponseEntity<DataResult<Void>>> Delete (@PathVariable @Min(1) Long id) {
        log.info ("Received request to delete company branch {}", id);
        return companyBranchService.deleteById (id).thenApply (result -> {
            DataResult<Void> response = new DataResult<> (
                    result,
                    true,
                    REMOVED
            );
            return ResponseEntity.ok (response);
        });
    }
}