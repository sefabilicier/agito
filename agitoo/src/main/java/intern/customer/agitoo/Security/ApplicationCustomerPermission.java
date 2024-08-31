package intern.customer.agitoo.Security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApplicationCustomerPermission {
    COMPANY_READ("company:read"),
    COMPANY_WRITE("company:write"),
    PERSON_READ("person:read"),
    PERSON_WRITE("person:write");

    private final String permission;
}
