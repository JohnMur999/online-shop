package ru.johnmur.online_shop.controllers.rest.versionconfigs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebRestConfigV1 implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        String apiVersion = "v1";
        configurer.addPathPrefix("/" + apiVersion, c ->
                c.getPackage().getName().startsWith("ru.johnmur.online_shop.controllers.rest.v1"));
    }
}
