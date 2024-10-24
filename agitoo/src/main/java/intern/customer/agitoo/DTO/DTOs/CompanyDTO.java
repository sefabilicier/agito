package intern.customer.agitoo.DTO.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import intern.customer.agitoo.Models.Concretes.CompanyBranch;
import intern.customer.agitoo.Models.Concretes.CompanyFinancial;
import intern.customer.agitoo.Models.Concretes.Customer;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {

    @NotNull(message = "companyId.notNull}")
    @Positive(message = "companyId.positive}")
    private Long companyId;

    @NotBlank(message = "{companyName.notBlank}")
    @Size(
            min = 2,
            max = 350,
            message = "{companyName.size}"
    )
    private String companyName;

    @NotBlank(message = "{industry.notBlank}")
    @Size(max = 50, message = "{industry.size}")
    private String industry;

    @NotNull(message = "{registrationNumber.NotNull}")
    @Size(min = 5, max = 10, message = "{registrationNumber.UpperAndLower}")
    private String registrationNumber;

    @NotNull(message = "{taxIdentificationNumber.notNull}")
    @Size(min = 9, max = 15, message = "{taxIdentificationNumber.characters}")
    private String taxIdentificationNumber;

    @NotNull(message = "{contactCompany.notNull}")
    @Size(min = 5, max = 10, message = "{contactCompany.characters}")
    private String contactCompany;

    @NotNull(message = "establishedDate.notNull}")
    @PastOrPresent(message = "establishedDate.PastorPresent}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date establishedDate;

    @PositiveOrZero(message = "{numberOfEmployees.positiveOrZero}")
    private Integer numberOfEmployees;

    @NotBlank(message = "{websiteURL.notBlank}")
    @Pattern(regexp = "^(https?://)?([\\w\\-]+\\.)+[\\w\\-]+(/[\\w\\- ./?%&=]*)?$", message = "{websiteURL.pattern}")
    private String websiteURL;

    @JsonIgnore
    private Customer customer;
    //    Long customerId = customerDTO.getCustomerId ();
    private Long customerId;

    @JsonIgnore
    private List<CompanyBranch> companyBranches;

    @JsonIgnore
    private List<CompanyFinancial> companyFinancials;

}