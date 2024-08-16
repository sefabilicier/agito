package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmploymentType {

    FullTime ("Full-Time"),
    PartTime ("Part-Time"),
    Contract ("Contract"),
    Internship ("Internship");

    private final String EmploymentType;
}
