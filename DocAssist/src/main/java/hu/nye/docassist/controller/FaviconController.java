package hu.nye.docassist.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class FaviconController implements WebMvcConfigurer{
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Explicit módon kezeli vagy ignorálja a favicon.ico kéréseket
        registry.addResourceHandler("/favicon.ico")
                .addResourceLocations("classpath:/static/");  // Adja hozzá a helyes útvonalat, ha van favicon
    }
}
