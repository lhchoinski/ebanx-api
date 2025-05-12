package com.ebanx.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Value("${server.port}")
    private int serverPort;

    @Bean
    public OpenAPI springOpenApiConfig() {

        List<Server> allServers = new ArrayList<>();

        Server serverLocal = new Server();
        serverLocal.url("http://localhost:" + serverPort);
        serverLocal.description("Local server");

        allServers.add(serverLocal);

        return new OpenAPI()
                .servers(allServers)
                .info(
                        new Info()
                                .title("Ebanx API")
                                .description("API for EBANX test")
                                .version("v1")
                                .contact(
                                        new Contact()
                                                .name("Luiz Henrique Choinski dos Santos.")
                                                .email("lhchoinski04@gmail.com")
                                )
                );
    }
}
