package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AddressType {

    Home ("Home"),
    Office ("Office"),
    Billing ("Billing"),
    Shipping ("Shipping");

    private final String AddressType;

}