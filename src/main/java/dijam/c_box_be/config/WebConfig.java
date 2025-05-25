package dijam.c_box_be.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://10.0.2.2:8080") // 허용할 Origin
                .allowedMethods("*") // 모든 HTTP 메서드 허용
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
