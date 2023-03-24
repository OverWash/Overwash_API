package com.meta.overwash.filter;


import com.meta.overwash.domain.UserDTO;
import com.meta.overwash.errorhandler.ForbiddenException;
import com.meta.overwash.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


public class MyFilter implements Filter {

    private final Environment env;

//    private UserService userService;
//    MyFilter(Environment env,UserService userService){
//        this.env = env;
//        this.userService =userService;
//    };
    MyFilter(Environment env){
        this.env = env;

    };


    @Override
    public void doFilter(ServletRequest request2, ServletResponse response2,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = (HttpServletResponse) response2;
        System.out.println("1111111111111111111111111");
        if (request.getHeader("AUTHORIZATION") == null) {
            onError(response, "UNAUTHORIZATION");
        } else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            String jwt = authorizationHeader.replace("Bearer", "");

            if (!isJwtValid(jwt)) {
                onError(response, "UNAUTHORIZATION2");
            }
        }
        try {
            System.out.println("11111111111111111");
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String role = ((UserDTO)user).getRole();
        boolean check =true;
            System.out.println(role);
        String url = request.getRequestURI();
            System.out.println(url);
            System.out.println(url.contains("member"));
        if(url.contains("member") && role.equals("ROLE_MEMBER")){
            check = false;
        }
        if(url.contains("admin") && role.equals("ROLE_ADMIN")){
            check = false;
        }
        if(url.contains("crew") &&  role.equals("ROLE_CREW")){
            check = false;
        }
        if(!(url.contains("member") || url.contains("crew") || url.contains("admin"))
        && !role.isEmpty()){
            check = false;
        }

        if(true){
            throw new ForbiddenException("접근 권한이 없습니다.");
        }
        chain.doFilter(request2, response2);
        }catch (Exception e){
            new NotFoundException("유저의 정보가 존재하지 않습니다.");

        }

    }

    private void onError(HttpServletResponse response, String httpStatus) throws IOException {
        response.addHeader("error", httpStatus);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, httpStatus);
    }

    private boolean isJwtValid(String jwt) {
        boolean resultValue = true;
        Claims  claims = null;

        try {
            claims = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody();

            if(claims == null || claims.isEmpty()){
                resultValue = false;

            }else{
                String subject = claims.getSubject();

//                Collection<? extends GrantedAuthority> authorities =
//                        Arrays.stream(claims.get("auth").toString().split(","))
//                                .map(SimpleGrantedAuthority::new)
//                                .collect(Collectors.toList());
//                UserDetails principal= userService.getUser(subject);
//                Authentication authentication = new UsernamePasswordAuthenticationToken
//                        (principal, "", authorities);
//                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            resultValue = false;
        }

        return resultValue;
    }
}
