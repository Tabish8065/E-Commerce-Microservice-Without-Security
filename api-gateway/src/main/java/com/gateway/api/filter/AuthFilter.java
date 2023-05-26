package com.gateway.api.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import com.gateway.api.routes.RouteValidator;
import com.gateway.api.service.ValidateService;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private ValidateService service;
	

    public AuthFilter() {
        super(Config.class);
    }
	
	@Override
	public GatewayFilter apply(Config config) { 
		// TODO Auto-generated method stub
		return ((exchange, chain) -> {
		
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				//Header has token or not
				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				
				if(authHeader == null || !authHeader.startsWith("Bearer ")) {
					throw new RuntimeException("No header present");
				}
				
				String token = authHeader.substring(7);
				System.out.println(token);
				
				try {
					service.isTokenValid(token);
				}catch (Exception e) {
					
					System.out.print(" GateWay Filter Error\n");
					throw new RuntimeException(e);
				}
				
			}
			
			return chain.filter(exchange);
		});
	}
	
	public static class Config {

    }
}
