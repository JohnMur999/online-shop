package ru.johnmur.online_shop.controllers.rest.versionconfigs;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice()
public class ControllerVersionAdvice {
    @ModelAttribute("apiVersion")
    public String apiVersion() {
        return "v1";
    }
}
