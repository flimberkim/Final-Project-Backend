package com.project.finalproject.global.config;

import com.project.finalproject.global.jwt.utils.JwtFilter;
import com.project.finalproject.global.jwt.utils.JwtProperties;
import com.project.finalproject.global.jwt.utils.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    // jwt 토큰을 사용하지 않는 URL
    String[] permitUrl = {"/**",
            "/company/login","/applicant/signup","applicant/checkemail",
            "/applicant/login","/company/signup", "/refresh","/admin/term/**"};


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http
                .cors()//기본 cors 설정
                .and()
                .csrf().disable() //위조요청 방지 비활성화
                .formLogin().disable() //formLogin 인증 비활성화
                .httpBasic().disable() //httpBasic 인증 비활성화
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(permitUrl)
                .permitAll()
                .anyRequest().authenticated();

        http
                .addFilterBefore(new JwtFilter(new JwtUtil(new JwtProperties())), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().mvcMatchers(permitUrl);
    }
}
