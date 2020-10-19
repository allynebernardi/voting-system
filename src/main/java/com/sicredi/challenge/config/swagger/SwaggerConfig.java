package com.sicredi.challenge.config.swagger;


import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket studentsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(challengeAPIInfo()).select()
                .apis(RequestHandlerSelectors.any()).paths(paths())
                .build();
    }

    private Predicate<String> paths() {
        return Predicates.or(
                regex("/v1/.*"));
    }

    private ApiInfo challengeAPIInfo() {
        return new ApiInfoBuilder().title("Vote API")
                .description("Voting System API ")
                .license("Apache License Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .version("1.0").build();
    }


}

