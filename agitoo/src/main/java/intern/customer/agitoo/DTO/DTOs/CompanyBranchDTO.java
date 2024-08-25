package intern.customer.agitoo.DTO.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyBranchDTO {

    @NotNull(message = " {branchName.notNull}")
    @NotBlank(message = "{branchName.notBlank}")
    @Size(min = 3, max = 150, message = "{branchName.size}")
    private String branchName;

    @NotBlank(message = "{branchAddress.notBlank}")
    @Size(max = 500, message = "{branchAddress.size}")
    private String branchAddress;

    @NotBlank(message = "{branchPhone.notBlank}")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "{branchPhone.pattern}")
    private String branchPhone;

//    private String companyName;

//    @Valid
//    private CompanyDTO company;

//    public String getCompanyName(){
//        return companySecondName ();
//    }
//
//
//    public String companySecondName(){
//        return company.getCompanyName ();
//    };

}