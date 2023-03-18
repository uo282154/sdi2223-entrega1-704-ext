package com.uniovi.sdi.sdi2223entrega171;

import com.uniovi.sdi.sdi2223entrega171.entities.Log;
import com.uniovi.sdi.sdi2223entrega171.handlers.LoginFailureHandler;
import com.uniovi.sdi.sdi2223entrega171.handlers.LoginSuccessHandler;
import com.uniovi.sdi.sdi2223entrega171.handlers.LogoutHandler;
import com.uniovi.sdi.sdi2223entrega171.services.LogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import java.sql.Timestamp;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LogoutHandler logoutHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LogService logService;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**", "/script/**", "/", "/signup", "/login/**","/error").permitAll()
                .antMatchers("/user/list/**").hasRole("ADMIN")
                .antMatchers("/log/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .successHandler(loginSuccessHandler)
                    .failureHandler(loginFailureHandler)
                .and()
                .logout()
                    .permitAll()
                    .logoutSuccessHandler(logoutHandler);
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

