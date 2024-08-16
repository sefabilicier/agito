package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {
    //'Credit Card', 'Bank Transfer', 'Cash', 'Other'

    CreditCard ("Credit Card"),
    BankTransfer ("Bank Transfer"),
    Cash ("Cash"),
    Other ("Other");


    private final String PaymentMethod;
}
