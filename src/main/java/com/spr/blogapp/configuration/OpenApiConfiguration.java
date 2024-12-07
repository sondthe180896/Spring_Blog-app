package com.spr.blogapp.configuration;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "Blog Application REST API",
                description = "REST API Documentation",
                termsOfService = "https://github.com/sondthe180896",
                contact = @Contact(
                        name = "Đặng Thái Sơn",
                        url = "https://github.com/sondthe180896",
                        email = "thaison02004@gmail.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"
                ),
                version = "v1.0.0"
        ),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8080"
        ),
        externalDocs = @ExternalDocumentation(
                description = "Github For Application",
                url = "http://github.com/sondthe810896/blogapp"
        )

)
public class OpenApiConfiguration {

}
