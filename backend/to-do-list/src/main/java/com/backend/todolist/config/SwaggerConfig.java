package com.backend.todolist.config;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket todoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.forCodeGeneration(true)
				.ignoredParameterTypes(Principal.class)
		        .globalOperationParameters(globalParameterList()) 
				.select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.todolist"))
                .build();
	}
	
	private List<Parameter> globalParameterList() {
	    Parameter authTokenHeader =
	        new ParameterBuilder()
	            .name("Authorization") // name of the header
	            .modelRef(new ModelRef("string"))
	            .required(false)
	            .parameterType("header")
	            .description("Bearer <token>")
	            .build();

	    return Collections.singletonList(authTokenHeader);
	  }
}
