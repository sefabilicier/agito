package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Priority {

    Low ("Low"),
    Medium ("Medium"),
    High ("High");

    private final String priority;
}
