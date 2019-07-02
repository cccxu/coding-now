package cn.cccxu.codingnow.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Configuration
public class StaticResourcesConfigruation implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/pictures/**").addResourceLocations("file:/coding-now/resource/pictures/");
        registry.addResourceHandler("/lessons/**").addResourceLocations("file:/coding-now/resource/lessons/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
