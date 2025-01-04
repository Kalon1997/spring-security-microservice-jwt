package com.askthem.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}

}



//spring.application.name=apigateway
//server.port=9000
//#eureka.client.enabled=true
//#eureka.client.register-with-eureka=false
//#eureka.client.fetch-registry=false
//#eureka.client.service-url.defaultZone=http://localhost:9000
//
//#spring.cloud.gateway.discovery.locator.enabled=true
//#spring.cloud.gateway.discovery.locator.lower-case-service-id=true
//
//# EUREKA RELATED
//eureka.client.service-url.defaultZone: ${EUREKA_URI:http://localhost:8761/eureka}
//eureka.instance.prefer-ip-address=true
//eureka.client.fetch-registry=true
//eureka.client.register-with-eureka=true	
//
//# ROUTING
//spring.cloud.gateway.routes[0].id = FORUM-USERS
//spring.cloud.gateway.routes[0].uri = lb://FORUM-USERS
//spring.cloud.gateway.routes[0].predicates[0] = Path=/auth/**
//#spring.cloud.gateway.mvc.routes[0].predicates[0].args[pattern]=/loginuser
//
//spring.cloud.gateway.routes[1].id = POSTS
//spring.cloud.gateway.routes[1].uri = lb://POSTS
//spring.cloud.gateway.routes[1].predicates[0] = Path=/all