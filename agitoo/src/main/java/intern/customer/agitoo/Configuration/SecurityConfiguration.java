package intern.customer.agitoo.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfiguration implements WebMvcConfigurer {

    //built for web security HTTP form submission
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırakma (geliştirme ortamında uygundur);
                .authorizeHttpRequests(
                        authorization -> authorization
                        .requestMatchers(
                                "/static/images/**",
                                "/person_add",
                                "/company_add",
                                "/templates/**",
                                "/api/**",
                                "/ui/**",
                                "/",
                                "/html/**",
                                "/index",
                                "/css/**",
                                "/js/**",
                                "/login",
                                "/logout"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(
                        formLogin -> formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .failureUrl("/login?error=true") //TODO: create a page
                                .defaultSuccessUrl("/home", true) // Başarıyla girişten sonra /customer URL'sine yönlendirme
                                .passwordParameter("password")
                                .usernameParameter("username")
                                .permitAll()
                )
                .rememberMe(
                        rememberMe -> rememberMe
                                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(7)) // Token geçerlilik süresi
                                .key("somethingVerySecured") // Remember-me key
                                .rememberMeParameter("remember-me")
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionFixation()
                                .newSession()
                                .maximumSessions(1)
                                .expiredUrl("/login-expired") // Oturum süresi dolduğunda yönlendirme URL'si
                )
                .logout(
                        logOut -> logOut
                                .logoutUrl("/logout")
                                .logoutRequestMatcher(
                                        new AntPathRequestMatcher("/logout", "POST"))
                                .clearAuthentication(true)
                                .invalidateHttpSession(true)
                                .deleteCookies("AGITOOID", "remember-me")
                                .logoutSuccessUrl("/login") // Logout sonrası yönlendirme
                );


        return http.build ();

    }
}