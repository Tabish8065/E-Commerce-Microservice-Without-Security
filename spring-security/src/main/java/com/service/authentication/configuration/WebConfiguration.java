package com.service.authentication.configuration;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.service.authentication.client.UserClient;

@Configuration
public class WebConfiguration {
	
	@Autowired
	private LoadBalancedExchangeFilterFunction filterFunction;

	@Bean
	public WebClient userWebClient() {
		return WebClient.builder()
				.baseUrl("http://user-service")
				.filter(filterFunction)
				.build();
	}
	
	@Bean
	public UserClient userClient() {
		HttpServiceProxyFactory proxyFactory = HttpServiceProxyFactory.builder(
				WebClientAdapter.forClient(
						userWebClient()
			)).blockTimeout(Duration.ofDays(1l))
				.build();
		
		return proxyFactory.createClient(UserClient.class);
	}
}
