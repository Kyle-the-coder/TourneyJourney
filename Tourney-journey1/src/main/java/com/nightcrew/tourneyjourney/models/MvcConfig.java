package com.nightcrew.tourneyjourney.models;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
 
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path eventUploadDir = Paths.get("./posted-img");
        String eventUploadPath = eventUploadDir.toFile().getAbsolutePath();
        
        registry.addResourceHandler("/posted-img/**").addResourceLocations("file:" + eventUploadPath + "/" );
        
    }
     
    
}
