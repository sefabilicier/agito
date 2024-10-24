package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum IsActive {
    YES ("YES"),
    NO ("NO");

    private final String isActive;
}
