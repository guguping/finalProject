package com.example.finalproject.config;

import com.example.finalproject.serivce.MemberSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final MemberSerivce memberSerivce;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/testmap/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
               .csrf().disable()
                 .authorizeRequests()
//                  .antMatchers("/", "/member/save", "/member/mailAuth", "/member/login").permitAll()
//                  .antMatchers("/board/**", "/media/**", "/member/update", "/member/delete").access("hasRole('ROLE_USER')")
                  .anyRequest().permitAll()
                .and()
                  .formLogin()
                   .loginPage("/")
                   .defaultSuccessUrl("/board/main")
                   .usernameParameter("memberEmail")
                   .passwordParameter("memberPassword")
                .and()
                 .logout()
                  .logoutSuccessUrl("/")
                   .invalidateHttpSession(true);
    }



//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}
