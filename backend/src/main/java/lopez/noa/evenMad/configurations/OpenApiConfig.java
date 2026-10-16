package lopez.noa.evenMad.configurations;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI openApi() {
        // REMEMBER mejorar la descripcion
        return new OpenAPI()
                .info(new Info().title("EVENMAD DOCUMENTATION").description(
                                """
                                           API of EvenMad to consult detailed `events` from the registered `venues`
                                        
                                           ### Suported Events categories
                                           - MATCH
                                           - CONCERT
                                        """
                        ).version("1.0.0").
                        contact(new Contact().name("Noaloprg").email("noaloprg@gmail.com")))

                .servers(List.of(
                        new Server().url("http://localhost:8080/api").description("local")
                ))

                .externalDocs(new ExternalDocumentation().
                        description("README of project").url("https://github.com/noaloprg/EvenMad"))
                ;
    }
}
