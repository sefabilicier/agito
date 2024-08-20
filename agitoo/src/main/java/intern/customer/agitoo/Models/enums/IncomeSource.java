package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IncomeSource {

    Primary ("Primary"),
    Secondary ("Secondary"),
    Investment ("Investment"),
    Other ("Other");

    private final String incomeSource;
}
