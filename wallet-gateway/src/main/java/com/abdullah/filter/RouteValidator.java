package com.abdullah.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> publicEndpoints = List.of(
            "/auth/register",
            "/auth/validate",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> publicEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));
    
}
