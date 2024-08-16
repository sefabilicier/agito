package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerType {

    COMPANY ("Company"),
    PERSON ("Person");

    private final String type;
}