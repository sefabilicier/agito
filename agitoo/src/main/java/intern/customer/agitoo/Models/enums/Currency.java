package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Currency {

    TR ("TR"),
    EUR ("EUR"),
    USD ("USD"),
    Other ("Other");

    private final String Currency;

}
