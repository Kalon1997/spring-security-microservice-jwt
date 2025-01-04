package com.askthem.apigateway.filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter  extends AbstractGatewayFilterFactory<AuthenticationFilter.Config>{
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private RestTemplate template;
	
	public static class Config {
    }
	
	public AuthenticationFilter() {
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange, chain) -> {
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Please provide authorization header");
				}
				
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if(authHeader != null && authHeader.startsWith("Bearer ")) {
//					authHeader = authHeader.substring(7);
				}
				
				try {
					template.getForObject("http://FORUM-USERS//auth/validateadmin" + authHeader, String.class);
					
				} catch (Exception e) {
					throw new RuntimeException("You are not authorized dear!!");
				}
			}
			return chain.filter(exchange);
		});
	}
}
