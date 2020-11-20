package com.backend.todolist.auth.jwt;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.backend.todolist.errorhandler.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtTokenFilter extends GenericFilterBean {
    private JwtTokenGenerator jwtTokenGenerator;
    
    public JwtTokenFilter(JwtTokenGenerator jwtTokenGenerator) {
        this.jwtTokenGenerator = jwtTokenGenerator;
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
    	try {
    		String token = jwtTokenGenerator.resolveToken((HttpServletRequest) req);
            if (token != null && jwtTokenGenerator.validateToken(token)) {
                Authentication auth = jwtTokenGenerator.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(req, res);
    	} catch (Exception ex) {
    		sendErrorResponse(HttpStatus.BAD_REQUEST, (HttpServletResponse) res, ex);
    	}
        
    }
    
    public void sendErrorResponse(HttpStatus status, HttpServletResponse response, Exception ex){
        response.setStatus(status.value());
        response.setContentType("application/json");
        
        CustomException customException = new CustomException(LocalDateTime.now(), status, ex.getMessage());
        
        try {
            response.getWriter().write(new ObjectMapper().writeValueAsString(customException));
        } catch (IOException e) {
        	
        }
    }
}