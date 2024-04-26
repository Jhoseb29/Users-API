package university.jala.usersapi.api;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI configuration for USERS-API documentation.
 */
@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "USERS-API",
        version = "3.1.0",
        description = "This is a CRUD for Users."
    )
)
public final class OpenApiConfig {

    /**
     * Configures OpenAPI documentation for the API.
     * @return Configured instance of OpenAPI.
     */
    @Bean
    public OpenAPI apiConfiguration() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement()
                .addList("Bearer Authentication"))
            .components(new Components().addSecuritySchemes(
                "Bearer Authentication", createApiSecurityScheme()));
    }

    /**
     * Creates the security scheme for JWT authentication.
     * @return Configured instance of SecurityScheme for JWT.
     */
    private SecurityScheme createApiSecurityScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
            .bearerFormat("JWT")
            .scheme("bearer");
    }
}
