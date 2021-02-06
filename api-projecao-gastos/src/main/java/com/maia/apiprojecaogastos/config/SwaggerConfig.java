package com.maia.apiprojecaogastos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.maia.apiprojecaogastos.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API - PROJEÇÕES DE GASTOS")
				.description(" Realiza o Cálculo de projeções de gastos, com base no preço do litro de combustível e o total de Km a ser percorrido.")
				.version("1.0")
				.contact(contact())
				.build();
	}

	private Contact contact() {
		return new Contact(	"Dowglas Maia", 
							"https://github.com/dowglasmaia", 
							"dowglasmaia@live.com");
	}
}
