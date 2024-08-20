package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmploymentType {

    FullTime ("FullTime"),
    PartTime ("PartTime"),
    Contract ("Contract"),
    Internship ("Internship");

    private final String employmentType;
}
