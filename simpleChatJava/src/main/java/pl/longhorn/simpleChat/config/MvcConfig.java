package pl.longhorn.simpleChat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("file:/home/jaksa/IdeaProjects/simpleChat-httpVsSocket/simpleChatJava/src/main/resources/static/")
                .setCacheControl(CacheControl.maxAge(1, TimeUnit.MINUTES).cachePublic());
    }
}