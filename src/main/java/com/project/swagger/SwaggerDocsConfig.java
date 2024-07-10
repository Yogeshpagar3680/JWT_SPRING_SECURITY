package com.project.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerDocsConfig {

    @Bean
    public Docket createDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.project.controller"))
                .paths(PathSelectors.regex("/api/customer.*"))
                .build()
                .useDefaultResponseMessages(true)
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        Contact contact = new Contact("avi", "http://www.ineuron.ai/course", "avi@gmail.com");
        return new ApiInfo("CustomerInfo", "Gives information about customer activities", "3.4.RELEASE", "http:", contact,
                "GNU PUBLIC", "http://apache.org/license/guru", Collections.emptyList());
    }
}
