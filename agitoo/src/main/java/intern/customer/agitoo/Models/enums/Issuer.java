package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Issuer {

    VisaInc ("VisaInc"),
    MasterCardInc ("MasterCardInc"),
    Troy ("Cash"),
    AmericanExpressCompany ("AmericanExpressCompany"),
    DiscoverFinancialServices ("DiscoverFinancialServices"),
    JCBCo ("JCBCo"),
    DinersClubInternational ("DinersClubInternational"),
    UnionPayInternational ("UnionPayInternational"),
    Maestro ("Maestro"),
    Elo ("Elo"),
    RuPay ("RuPay");

    private final String issuer;
}
