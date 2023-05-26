 package com.gateway.api.routes;

import java.util.*;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

	
	public static final List<String> openApiEndpoints = List.of(
			"/user/register",
			"/user/getByUID",
			"/user/delete",
			"/user/update",
			"/product/getAll",
			"/product/getById",
			"/order/register",
			"/order/getById",
			"/order/returnById",
			"/auth/token",
			"/auth/validate",
			"/eureka"
	);
	
	public Predicate<ServerHttpRequest> isSecured = 
			request -> openApiEndpoints
				.stream()
				.noneMatch(uri -> request.getURI().getPath().contains(uri));
	
}