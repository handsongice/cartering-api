package cn.jy.filter;

import cn.jy.util.JWTUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String authHeader = request.getHeader("auth");
        if (authHeader == null || !authHeader.startsWith("Cartering ")) {
            throw new ServletException("Missing or invalid Authorization header.");
        }
        final String token = authHeader.substring(10);
        try {
            final Claims claims = JWTUtils.parse(token);
            request.setAttribute("claims", claims);
        } catch (final SignatureException e) {
            throw new ServletException("Invalid token.");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
