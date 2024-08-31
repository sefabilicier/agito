package intern.customer.agitoo.Configuration.Rules;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

public class SecurityRules {

    @Bean
    public static UserDetailsService userDetailsService () {
        return new InMemoryUserDetailsManager (
                User.withUsername ("user")
                        .password ("{noop}password")  // Noop, şifreyi şifrelemeden kullanmak için
                        .roles ("USER")
                        .build ()
        );
    }
}
