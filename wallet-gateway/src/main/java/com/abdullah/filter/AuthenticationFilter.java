package com.abdullah.filter;

import com.abdullah.exception.InvalidTokenException;
import com.abdullah.exception.MissingAuthorizationHeaderException;
import com.abdullah.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil util;
    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) ->{
         if (routeValidator.isSecured.test(exchange.getRequest())){
             if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                 try {
                     throw new MissingAuthorizationHeaderException("Missing authorization header");
                 } catch (MissingAuthorizationHeaderException e) {
                     throw new RuntimeException(e);
                 }
             }
             String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
             if (authHeader != null && authHeader.startsWith("Bearer ")){
                 authHeader = authHeader.substring(7);
             }
             try {
                 util.validate(authHeader);
             }catch (Exception e){
                 try {
                     throw new InvalidTokenException("Invalid token");
                 } catch (InvalidTokenException ex) {
                     throw new RuntimeException(ex);
                 }
             }
         }
            return chain.filter(exchange);
        });
    }

    public static class Config{

    }

}
