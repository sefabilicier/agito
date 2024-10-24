package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Issuer {

    //Issuer IN (
    // 'Visa Inc',
    // 'Mastercard Inc',
    // 'Troy',
    // 'American Express Company',
    // 'Discover Financial Services',
    // 'JCB Co',
    // 'Diners Club International',
    // 'UnionPay International',
    // 'Maestro',
    // 'Elo',
    // 'RuPay')

    VisaInc ("Visa Inc"),
    MastercardInc ("Mastercard Inc"),
    Troy ("Troy"),
    AmericanExpressCompany ("American Express Company"),
    DiscoverFinancialServices ("Discover Financial Services"),
    JCBCo ("JCB Co"),
    DinersClubInternational ("Diners Club International"),
    UnionPayInternational ("UnionPay International"),
    Maestro ("Maestro"),
    Elo ("Elo"),
    RuPay ("RuPay");

    private final String issuer;

}
