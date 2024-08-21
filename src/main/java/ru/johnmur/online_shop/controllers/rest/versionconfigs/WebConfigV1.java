package ru.johnmur.online_shop.controllers.rest.versionconfigs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigV1 implements WebMvcConfigurer {
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("/v1", c ->
                c.getPackage().getName().startsWith("ru.johnmur.online_shop.controllers.rest.v1"));
    }
}
