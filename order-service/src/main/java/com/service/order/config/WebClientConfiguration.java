package com.service.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.service.order.client.ProductClient;
import com.service.order.client.UserClient;

@Configuration
public class WebClientConfiguration {
	
	@Autowired
	private LoadBalancedExchangeFilterFunction filterFunction;

	@Bean
	public WebClient productWebClient() {
		return WebClient.builder()
				.baseUrl("http://product-service")
				.filter(filterFunction)
				.build();
	}
	
	@Bean
	public ProductClient productClient() {
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory
				.builder(
						WebClientAdapter.forClient(productWebClient()))
				.build();
		
		return proxyFactory.createClient(ProductClient.class);
	}
	
	@Bean
	public WebClient userWebClient() {
		return WebClient.builder()
				.baseUrl("http://user-service")
				.filter(filterFunction)
				.build();
	}
	
	@Bean
	public UserClient userClient() {
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory
				.builder(
						WebClientAdapter.forClient(userWebClient()))
				.build();
		
		return proxyFactory.createClient(UserClient.class);
	}
	
}
