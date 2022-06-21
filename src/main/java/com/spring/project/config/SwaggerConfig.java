package com.spring.project.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket createDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.spring.project.controller"))
				.paths(PathSelectors.regex("/api.*"))
				.build()
				.apiInfo(createApiInfo())
				;
	}
	
	private ApiInfo createApiInfo() {
		return new ApiInfo(
				"Employee CRUD",
				"This is an mini-applciation", 
				"1.0.0", 
				"https://karriprakash.github.io/Portfolio.github.io/", 
				new Contact("Surya", "https://karriprakash.github.io/Portfolio.github.io/", "karriprakash342@gmail.com"),
				"license", 
				"License-2022",
				Collections.emptyList()
				);
	}
}
