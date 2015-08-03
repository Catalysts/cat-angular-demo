package cc.catalysts.angular.spring.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * Created by thomas.scheinecker on 31.07.2015.
 */
@Configuration
@EnableWebSecurity
public class CatAngularSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        // permit all static resources
        webSecurity.ignoring()
                .antMatchers("/")
                .antMatchers("/css/**")
                .antMatchers("/js/**")
                .antMatchers("/images/**")
                .antMatchers("/webjars/**")
                .antMatchers("/favicon.ico");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return new User(username, username, Collections.<GrantedAuthority>emptyList());
            }
        }).passwordEncoder(new PlaintextPasswordEncoder());
    }
}
