package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SalaryFrequency {

    Monthly ("Monthly"),
    BiWeekly ("BiWeekly"),
    Weekly ("Weekly"),
    Annually ("Annually");

    private final String salaryFrequency;

}
