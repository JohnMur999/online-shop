package ru.johnmur.online_shop.controllers.admintools;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdminToolsPath implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/admin", c ->
                c.getPackage().getName().startsWith("ru.johnmur.online_shop.controllers.admintools"));
    }
}
