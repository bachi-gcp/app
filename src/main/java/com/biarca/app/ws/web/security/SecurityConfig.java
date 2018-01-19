package com.biarca.app.ws.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/app/**").hasAnyAuthority("USER", "ADMIN").anyRequest()
        .fullyAuthenticated().and().csrf().disable().formLogin().loginPage("/app/login")
        .failureUrl("/app/login?error").usernameParameter("id").passwordParameter("password")
        .defaultSuccessUrl("/app/dashboard").permitAll().and().logout().logoutUrl("/logout")
        .logoutSuccessUrl("/").permitAll().and().sessionManagement().maximumSessions(10)
        .expiredUrl("/").and().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .invalidSessionUrl("/");

    http.authorizeRequests().antMatchers("/api/**").hasAnyAuthority("USER", "ADMIN").anyRequest()
        .fullyAuthenticated().and().httpBasic().and().csrf().disable();

    http.authorizeRequests().antMatchers("/app/login").permitAll();

    http.authorizeRequests().antMatchers("/").permitAll();
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
  }
}
