package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {

    @NotBlank
    private String companyname;
    @NotBlank
    private String industry;

    @PositiveOrZero
    private Integer numberofemployees;

    @NotBlank
    private String websiteurl;

    private List<CompanyBranchDTO> companyBranches;
    private List<CompanyFinancialDTO> companyFinancials;

    private CustomerDTO customer;

}

