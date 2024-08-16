package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SalaryFrequency {

    Monthly ("Monthly"),
    BiWeekly ("Bi-Weekly"),
    Weekly ("Weekly"),
    Annualy ("Annually");

    private final String SalaryFrequency;

}
