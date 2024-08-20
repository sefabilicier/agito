package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PaymentMethod {

    CreditCard ("CreditCard"),
    BankTransfer ("BankTransfer"),
    Cash ("Cash"),
    Other ("Other");


    private final String paymentMethod;
}
