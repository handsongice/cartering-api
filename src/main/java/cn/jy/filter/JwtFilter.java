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
        final String authHeader = request.getHeader("token");
        if (authHeader == null || !authHeader.startsWith("Cartering ")) {
            throw new ServletException("请先登录！");
        }
        final String token = authHeader.substring(10);
        try {
            Claims claims = JWTUtils.parse(token);
            if(null == claims) {
                throw new ServletException("登录超时！");
            }
            request.setAttribute("claims", claims);
        } catch (final SignatureException e) {
            throw new ServletException("登录超时！");
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
