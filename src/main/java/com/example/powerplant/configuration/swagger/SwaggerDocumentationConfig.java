package com.example.powerplant.configuration.swagger;

import org.springframework.beans.factory.annotation.Value;
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

//@Configuration
public class SwaggerDocumentationConfig {

//    @Bean
//    public Docket apiDocket() {
//
//        return new Docket(DocumentationType.SWAGGER_2)
////                .apiInfo(getApiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo getApiInfo() {
//
//        return new ApiInfoBuilder()
//                .title("Swagger API Doc")
//                .description("More description about the API")
//                .version("1.0.0")
//                .build();
//    }
}