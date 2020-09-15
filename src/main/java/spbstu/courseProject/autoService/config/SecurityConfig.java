package spbstu.courseProject.autoService.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import spbstu.courseProject.autoService.auth.UserService;
import spbstu.courseProject.autoService.dto.RegistrationDto;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(UserService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/*.js", "/*.css").permitAll()
                .antMatchers("/templates/login.html", "/templates/registration.html").not().authenticated()
                .antMatchers("/logout").authenticated()
                .antMatchers(HttpMethod.POST, "/api/auth").not().authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/templates/masters.html")
                .loginPage("/templates/login.html")
                .loginProcessingUrl("/login")
                .and()
                .logout().logoutSuccessUrl("/templates/login.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        auth.authenticationProvider(provider);
    }

}
