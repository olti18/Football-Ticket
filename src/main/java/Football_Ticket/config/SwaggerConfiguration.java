package Football_Ticket.config;

//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
//@EnableSwagger2
//@EnableWebSecurity
public class SwaggerConfiguration {


    public static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.backend.testManagement.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .groupName("v1"); // Set the API version here
    }


    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";

        // Create a SecurityRequirement instance and add the scheme
        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList(securitySchemeName);

        // Configure OpenAPI instance
        return new OpenAPI()
                .info(new Info()
                        .title("Test Backend API")
                        .version("2.0")
                        .description("API documentation for the Test Backend application"))
                .addSecurityItem(securityRequirement) // Add the configured security requirement
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Test Backend")
                .description("API documentation")
                .version("1.0")
                .build();
    }




}
