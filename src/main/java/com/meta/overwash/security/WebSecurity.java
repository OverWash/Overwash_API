package com.meta.overwash.security;

import com.meta.overwash.filter.MyFilter;
import com.meta.overwash.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurity extends WebSecurityConfigurerAdapter {
    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final Environment env;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 어떤 권한에 대해서 작업을 수행하고 싶을 때. 권한 처리 수행

        http.csrf()
                .disable()   // csrf 토큰
                .httpBasic().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/error/**").permitAll()
                .antMatchers("/register/**").permitAll()
                .antMatchers("/check/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasRole("MEMBER")
//                .antMatchers("/reservations/**").hasRole("MEMBER")
                .antMatchers("/crew/**").access("ROLE_CREW")
                .antMatchers("/**").permitAll()// 모든 요청
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilterAfter(new MyFilter(env,userService)
                        , UsernamePasswordAuthenticationFilter.class) // 로그인 이후
                .logout()
                .invalidateHttpSession(true)
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                });
    }

    // 넘어오는 token 을 filter 로 생성
    private Filter getAuthenticationFilter() throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService, env);
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // 권한 토큰 생성 및 필터링이 완성되면 그 권한을 통해 인증 처리
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
    }


}
