package ir.dc.userAuthenticator.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration {
//    first must disable spring security cors then add this config:
    //https://docs.spring.io/spring-security/reference/reactive/integrations/cors.html

    @Value("${live.allow.origin}")
    private String liveAllowOrigin;
    @Value("${develop.allow.origin}")
    private String developAllowOrigin;

    @Bean
    @Profile(value = {"prod-env","test-env"})
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOriginPatterns(developAllowOrigin);
            }
        };
    }
    @Bean
    @Profile("live-env")
    public WebMvcConfigurer corsConfigurerProd() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "PUT", "POST", "DELETE").allowedOriginPatterns(liveAllowOrigin);
            }
        };
    }
}