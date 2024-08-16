package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerPolicyType {

    Life ("Life"),
    Health ("Health"),
    Auto ("Auto"),
    Home ("Home"),
    Travel ("Travel");

    private final String CustomerPolicyType;

}
