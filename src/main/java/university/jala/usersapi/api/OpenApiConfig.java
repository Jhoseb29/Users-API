package university.jala.usersapi.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
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
public class OpenApiConfig {

  /**
   * The URL of the server.
   */
  @Value("${server.url}")
  private String serverUrl;

  /**
   * Configures OpenAPI documentation for the API.
   *
   * @return Configured instance of OpenAPI.
   */
  @Bean
  public OpenAPI apiConfiguration() {
    var openApi = new OpenAPI()
        .addSecurityItem(
            new SecurityRequirement().addList("Bearer Authentication"))
        .components(new Components().addSecuritySchemes(
            "Bearer Authentication", createApiSecurityScheme()));

    if (!serverUrl.equals("localhost")) {
      openApi = new OpenAPI()
          .addServersItem(new Server().url(serverUrl).description("Server URL"));
    }
    return openApi;
  }

  /**
   * Creates the security scheme for JWT authentication.
   *
   * @return Configured instance of SecurityScheme for JWT.
   */
  private SecurityScheme createApiSecurityScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
        .bearerFormat("JWT")
        .scheme("bearer");
  }
}
