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

    private static final String[] PUBLIC_MATCHERS = {
            "/register",
            "/h2-console/**"
    };

    private static final String[] APPLICANT_EMPLOYER_MATCHERS = {
            "/resume",
            "/letter",
            "/secure",
            "/",
    };

    private static final String[] APPLICANT_MATCHERS = {
            "/summary",
            "/summary/**",
            "/contact",
            "/contact/**",
            "/education",
            "/education/**",
            "/experience",
            "/experience/**",
            "/skill",
            "/skill/**",
            "/reference"
    };


    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUserDetailsService(userRepository);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers(PUBLIC_MATCHERS).permitAll()
                    .antMatchers(APPLICANT_EMPLOYER_MATCHERS).hasAnyAuthority("EMPLOYER", "APPLICANT")
                    .antMatchers(APPLICANT_MATCHERS).hasAuthority("APPLICANT")
                        .anyRequest().authenticated()
                        .and()
                    .formLogin()
                        .loginPage("/login").permitAll()
                        .and()
                    .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .and()
                    .httpBasic();
        http
                .csrf().disable();
        http
                .headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .userDetailsService(userDetailsServiceBean());
    }
}
