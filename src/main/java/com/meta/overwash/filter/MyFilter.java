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
import java.util.ArrayList;
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

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        if (checkUrl((HttpServletRequest) request)) {

            String token = getToken((HttpServletRequest) request);
            if (token != null && isJwtValid(token)) {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                onError((HttpServletResponse) response, "UNAUTHORIZATION");
            }
        }
            chain.doFilter(request, response);
    }

    private static boolean compareStrings(List<String> arr, String s2) {
        for (String s1 : arr) {
            int index1 = s1.lastIndexOf('/');
            int index2 = s2.lastIndexOf('/');

            if (index1 == -1 || index2 == -1) {
                if (s1.equals(s2)) {
                    return false;
                }
            } else {
                String sub1 = s1.substring(0, index1);
                String sub2 = s2.substring(0, index2);

                if (sub1.equals(sub2)) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkUrl(HttpServletRequest req) {
        List<String> uriList = new ArrayList<String>();
        uriList.add("/check");
        uriList.add("/check/member/");
        uriList.add("/check/crew/");
        uriList.add("/register/");

        String uri = req.getRequestURI();
        return compareStrings(uriList,uri);

    }

    /*
        jwt에서 role을 가져와
        UsernamePasswordAuthenticationTokensecurity 에서 권한 처리 리턴
     */
    private Authentication getAuthentication(String token) {

        Claims claims = parseClaims(token); // 토큰 복호화
        UserDTO user = new UserDTO();
        String role = claims.get("auth").toString(); // auth에 관련된 내용 가져옴

        Collection<? extends GrantedAuthority> authorities = // 권한을 Collection<GrantedAuthority> 로 변환
                Arrays.stream(role.split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        user.setUserId(Long.parseLong(claims.get("userId").toString()));
        user.setEmail(claims.getSubject());
        user.setRole(role);
        UserDetails principal = user;
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private String getToken(HttpServletRequest request) {
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
