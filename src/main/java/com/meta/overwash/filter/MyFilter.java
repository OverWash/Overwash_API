package com.meta.overwash.filter;


import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class MyFilter implements Filter {

    private Environment env;

    private UserService userService;

    public MyFilter(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    ;

    MyFilter(Environment env) {
        this.env = env;

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        String token = getToken((HttpServletRequest) request);
        if (token != null && isJwtValid(token)) {
            Authentication authentication = getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            onError((HttpServletResponse) response, "UNAUTHORIZATION");
        }
        chain.doFilter(request, response);
    }


    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        UserDTO user = new UserDTO();
        String role= claims.get("auth").toString();

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(role.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        user.setEmail(claims.getSubject());
        user.setRole(role);
        UserDetails principal = user;
        return new UsernamePasswordAuthenticationToken(principal,"",authorities);
    }

    public String getToken(HttpServletRequest request) {
        //Request header에서 AUTHORIZATION 가져옴
        String authorizationHeader = request.getHeader("AUTHORIZATION");
        // Bearer+" " 를 제외하고 jwt 토큰을 리턴
        return authorizationHeader.replace("Bearer", "");
    }

    private void onError(HttpServletResponse response, String httpStatus) throws IOException {
        response.addHeader("error", httpStatus);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, httpStatus);
    }

    private boolean isJwtValid(String token) {
        try {
            Jwts.parser().setSigningKey(env.getProperty("token.secret")).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
