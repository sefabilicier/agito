package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerType {

    Company ("Company"),
    Person ("Person");

    private final String customerType;
}