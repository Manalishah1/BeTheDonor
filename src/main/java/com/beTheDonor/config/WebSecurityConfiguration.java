package com.beTheDonor.config;


import com.beTheDonor.service.ApplicationUserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final ApplicationUserService applicationUserService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/**","/styles/css/**","/images/**","/templates/**","/js/**","/donate","/thank-you").permitAll()
                .antMatchers("/accessdenied","/authenticate","userLogin")
                .permitAll()
                .antMatchers("/loginSuccess").hasAnyAuthority("ADMIN")
                .antMatchers("/riderDashboard").hasAnyAuthority("Rider")
                .antMatchers("/donorview").hasAnyAuthority("Donor")
                .antMatchers("/patient/**").hasAnyAuthority("Patient")
                .anyRequest()
                .authenticated().and()
                .formLogin().loginPage("/api/v1/login").usernameParameter("email")
                .and()
                .logout().invalidateHttpSession(true).clearAuthentication(true).logoutSuccessUrl("/logoutSuccessful").permitAll().and().exceptionHandling().accessDeniedPage("/accessdenied")
        ;

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
