package intern.customer.agitoo.Models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActivityType {

    Login ("Login"),
    ProfileUpdate ("ProfileUpdate"),
    Purchase ("Purchase"),
    Claim ("Claim");

    private final String ActivityType;
}
