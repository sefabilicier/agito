package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsDefault {
    YES ("YES"),
    NO ("NO");

    private final String isDefault;
}
