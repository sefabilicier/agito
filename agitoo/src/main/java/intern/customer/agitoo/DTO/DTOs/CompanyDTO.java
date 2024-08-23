package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "{companyName.notBlank}")
    @Size(min = 2, max = 350, message = "{companyName.size}")
    private String companyName;

    @NotBlank(message = "{industry.notBlank}")
    @Size(max = 50, message = "{industry.size}")
    private String industry;

    @PositiveOrZero(message = "{numberOfEmployees.positiveOrZero}")
    private Integer numberOfEmployees;

    @NotBlank(message = "{websiteURL.notBlank}")
    @Pattern(regexp = "^(https?://)?([\\w\\-]+\\.)+[\\w\\-]+(/[\\w\\- ./?%&=]*)?$", message = "{websiteURL.pattern}")
    private String websiteURL;

    @Valid
    private List<CompanyBranchDTO> companyBranches;

    @Valid
    private List<CompanyFinancialDTO> companyFinancials;

//    @Valid
//    private CustomerDTO customer;

}