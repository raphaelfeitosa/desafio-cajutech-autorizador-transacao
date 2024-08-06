package caju.tech.transactionauthorizer.adapter.ports.input.api.swagger

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Transaction Authorizer API")
                    .description("API for authorizing transactions")
                    .version("v1.0.0")
            )
    }
}