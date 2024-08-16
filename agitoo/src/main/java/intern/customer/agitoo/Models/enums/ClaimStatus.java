package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClaimStatus {

    Pending ("Pending"),
    Approved ("Approved"),
    Rejected ("Rejected");

    private final String ClaimStatus;
}
