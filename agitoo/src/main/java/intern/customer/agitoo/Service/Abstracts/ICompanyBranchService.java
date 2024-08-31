package intern.customer.agitoo.Service.Abstracts;

import intern.customer.agitoo.DTO.DTOs.CompanyBranchDTO;
import org.springframework.data.jpa.repository.Query;


public interface ICompanyBranchService
        extends IGenericService<CompanyBranchDTO> {

    @Query(
            "SELECT " +
                    "CASE WHEN COUNT(c) > 0 " +
                    "THEN 'Wuh! We already have it...' " +
                    "ELSE 'processing.' " +
                    "END " +
                    "FROM CompanyBranch c " +
                    "WHERE c.branchName = :name")
    String existsByName (String name);
}
