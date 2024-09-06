package intern.customer.agitoo.Security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Set;
import java.util.stream.Collectors;

import static intern.customer.agitoo.Security.ApplicationCustomerPermission.*;

@Getter
@AllArgsConstructor
public enum ApplicationCustomerRole {
    PERSON(Sets.newHashSet (PERSON_READ, PERSON_WRITE)),
    COMPANY(Sets.newHashSet (COMPANY_READ, COMPANY_WRITE));

    private final Set<ApplicationCustomerPermission> permissions;

    public Set<SimpleGrantedAuthority> getSimpleGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }


    }
