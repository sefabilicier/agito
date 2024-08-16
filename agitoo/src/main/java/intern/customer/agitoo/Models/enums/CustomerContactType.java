package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomerContactType {

    Primary ("Primary"),
    Support ("Support"),
    Sales ("Sales"),
    Other ("Other");

    private final String CustomerContact;

}
