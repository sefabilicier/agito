package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyBranchDTO {


    @NotNull(message = "Branch name cannot be null")
    @NotBlank
    private String branchName;
    @NotBlank
    private String brachAddress;
    @NotBlank
    private String branchPhone;

    private CompanyDTO company;
}
