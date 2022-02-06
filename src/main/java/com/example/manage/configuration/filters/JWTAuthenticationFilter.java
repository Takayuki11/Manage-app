package com.example.manage.configuration.filters;

import com.example.manage.JWTProvider;
import io.jsonwebtoken.Claims;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter
public class JWTAuthenticationFilter implements Filter {

    private static Log log = LogFactory.getLog(JWTAuthenticationFilter.class);
    private JWTProvider provider;

    @Autowired
    public JWTAuthenticationFilter(JWTProvider provider){
        this.provider = provider;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException{
        log.debug("init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("filtering");
        String token = this.provider.getToken((HttpServletRequest) request);
        if(token != null && this.provider.validateToken(token)){
            Claims claims = this.provider.getClaims(token);
            var currentAttribute = RequestContextHolder.currentRequestAttributes();
            currentAttribute.setAttribute("JWTPayload", claims, RequestAttributes.SCOPE_REQUEST);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy(){
        log.debug("destroying");
    }
}
