package com.demo.merchant.sso.config;

import com.demo.merchant.sso.service.CustomUserDetailsService;
import com.demo.merchant.sso.service.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        //remember me
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll().successHandler(loginSuccessHandler())
                .and().authorizeRequests()
                .antMatchers("/images/**", "/checkcode", "/scripts/**", "/styles/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and().exceptionHandling().accessDeniedPage("/deny")
                .and().rememberMe().tokenValiditySeconds(86400).tokenRepository(tokenRepository());//记住24小时
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JdbcTokenRepositoryImpl tokenRepository(){
        JdbcTokenRepositoryImpl jtr = new JdbcTokenRepositoryImpl();
        jtr.setDataSource(dataSource);
        return jtr;
    }

    @Bean
    public LoginSuccessHandler loginSuccessHandler(){
        return new LoginSuccessHandler();
    }
}
