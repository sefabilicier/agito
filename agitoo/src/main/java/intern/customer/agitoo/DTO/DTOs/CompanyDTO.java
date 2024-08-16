package intern.customer.agitoo.DTO.DTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompanyDTO {

    private String companyname;
    private String industry;
    private Integer numberofemployees;
    private String websiteurl;


}

