package com.example.demo.backend.config;

import com.example.demo.backend.repositories.UserRepository;
import com.example.demo.backend.services.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    private static final String[] PRIVATE_MATCHERS = {
            "/contact",
            "/education",
            "/experience",
            "/resume",
            "/skill",
            "/contact/**",
            "/education/**",
            "/experience/**",
            "/resume/**",
            "/skill/**"
    };

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUserDetailsService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/", "/h2-console/**", "/register").permitAll()
                    .antMatchers(PRIVATE_MATCHERS).access("hasAuthority('ROLE_EMPLOYER') or hasAuthority('ROLE_APPLICANT')")
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                        .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .logoutSuccessUrl("/login").permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsServiceBean());
    }
}
