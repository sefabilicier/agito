package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MaritalStatus {

    Single ("Single"),
    Married ("Married"),
    Divorced ("Divorced"),
    Widowed ("Widowed");

    private final String maritalStatusType;

}
