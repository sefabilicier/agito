package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PersonGender {
    Male ("Male"),
    Female ("Female"),
    Other ("Other");

    private final String genderType;
}
