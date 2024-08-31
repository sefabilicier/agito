package intern.customer.agitoo.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    //built for web security HTTP form submission
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırakma (geliştirme ortamında uygundur)
                .authorizeHttpRequests(
                        authz -> authz
                        .requestMatchers("/api/**","/", "/html/*", "index", "/css/*", "/js/*")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                ).formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .permitAll()
                                .defaultSuccessUrl("/customer/get-all", true) // Başarıyla girişten sonra /customer URL'sine yönlendirme
                                .passwordParameter("password")
                                .usernameParameter("username")
                ).rememberMe(
                        rememberMe -> rememberMe
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7)) // Token geçerlilik süresi
                                .key("somethingVerySecured") // Remember-me key
                                .rememberMeParameter("remember-me")
                ).logout(
                        logOut -> logOut
                                .logoutUrl("/logout")
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("AGITOOID", "remember-me")
                                .logoutSuccessUrl("/login") // Logout sonrası yönlendirme
                );

                /*
                .sessionManagement(
                        sessionManagement -> sessionManagement
                        .sessionFixation()
                                .newSession()
                        .maximumSessions(1)
                        .expiredUrl("/login?expired") // Oturum süresi dolduğunda yönlendirme URL'si
                )
                ;*/

        return http.build ();

    }
}
