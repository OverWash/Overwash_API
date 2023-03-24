package com.meta.overwash.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserService userService;

    private Environment env;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                UserService userService, Environment env) {
        super.setAuthenticationManager(authenticationManager);
        this.userService = userService;
        this.env = env;
    }

    // 로그인 버튼을 누르는 순간 가장 먼저 호출되는 함수. 인증 요청
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            System.out.println("1111111111111");
            // 로그인 요청 시 넘어오는 객체
            System.out.println(request.getInputStream());
            System.out.println(UserDTO.class);
            UserDTO user = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
            //여기까지는 넘어옴
            System.out.println(user);
            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 인증이 성공하면 호출되는 함수.
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((UserDTO) authResult.getPrincipal()).getUsername();
        System.out.println("username: " + username);
        UserDTO userDetail = userService.getUser(username);

        String token = Jwts.builder()
                .setSubject(userDetail.getEmail())
                .setExpiration(new Date(System.currentTimeMillis() +
                        Long.parseLong(env.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
                .compact();

        response.addHeader("role",userDetail.getRole());
        Cookie cookie = new Cookie("token",token);

        response.addCookie(cookie);
    }
}
