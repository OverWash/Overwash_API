package com.meta.overwash.filter;


import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class MyFilter implements Filter {

    private final Environment env;

    @Override
    public void doFilter(ServletRequest request2, ServletResponse response2,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) request2;
        HttpServletResponse response = (HttpServletResponse) response2;

        if (request.getHeader("AUTHORIZATION") == null) {
            onError(response, "UNAUTHORIZATION");
        } else {
            String authorizationHeader = request.getHeader("AUTHORIZATION");
            String jwt = authorizationHeader.replace("Bearer", "");

            if (!isJwtValid(jwt)) {
                onError(response, "UNAUTHORIZATION2");
            }

        }
        chain.doFilter(request2, response2);

    }

    private void onError(HttpServletResponse response, String httpStatus) throws IOException {
        response.addHeader("error", httpStatus);
        response.sendError(HttpServletResponse.SC_FORBIDDEN, httpStatus);
    }

    private boolean isJwtValid(String jwt) {
        boolean resultValue = true;
        String subject = null;
        try {
            subject = Jwts.parser().setSigningKey(env.getProperty("token.secret"))
                    .parseClaimsJws(jwt)
                    .getBody().getSubject();
            resultValue = !(subject == null || subject.isEmpty());

        } catch (Exception e) {
            resultValue = false;
        }
        return resultValue;
    }
}
