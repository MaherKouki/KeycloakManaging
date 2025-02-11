package Keycloak_Auth_Service.Keycloak_auth_service.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> {
            web.ignoring().requestMatchers(
                    HttpMethod.POST,
                    "/public/**",
                    "/users"
            );

            web.ignoring().requestMatchers(
                    HttpMethod.GET,
                    "/public/**"
            );


            web.ignoring().requestMatchers(
                    HttpMethod.DELETE,
                    "/public/**",
                    "/users/{id}"
            );


            web.ignoring().requestMatchers(
                    HttpMethod.PUT,
                    "/public/**",
                    "/users/{id}/send-verification-email",
                    "/users/forgot-password"
                    //,"/roles/assign/users/"
            );


            web.ignoring().requestMatchers(
                    HttpMethod.OPTIONS,
                    "/public/**"
            )
                    .requestMatchers(
                            "/v3/api-docs/**" , "/configuration/**" , "/swagger-ui/**",
                            "/swagger-resources/**" ,"/swagger-ui.html", "/webjars/**" , "/api-docs/**"
                    );

        };
    }



}
