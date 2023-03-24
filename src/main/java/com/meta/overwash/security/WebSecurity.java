package com.meta.overwash.security;


import com.meta.overwash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder;

    private Environment env;
    public WebSecurity(UserService userService, BCryptPasswordEncoder passwordEncoder,
                       Environment env) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.env = env;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 어떤 권한에 대해서 작업을 수행하고 싶을 때. 권한 처리 수행

        http.csrf().disable(); // csrf 토큰

        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/error/**").permitAll();
//        http.authorizeRequests().antMatchers("/member/**").access("hasRole(ROLE_MEMBER)");
//        http.authorizeRequests().antMatchers("/crew/**").access("hasRole(ROLE_CREW)");
//        http.authorizeRequests().antMatchers("/admin/**").access("hasRole(ROLE_ADMIN)");

        http.authorizeRequests().antMatchers("/**")// 모든 요청
                .hasIpAddress("127.0.0.1")
                .and()
                .addFilter(getAuthenticationFilter())
                ; // 로그인을 거쳐야 함



    }


    // 넘어오는 token 을 filter 로 생성
    private Filter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService,env);
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 권한 토큰 생성 및 필터링이 완성되면 그 권한을 통해 인증 처리
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


}
