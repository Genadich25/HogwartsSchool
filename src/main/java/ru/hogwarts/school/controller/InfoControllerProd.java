package ru.hogwarts.school.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.school.controller.interfaceControllers.InfoControllerInterface;

@RestController
@Profile("prod")
public class InfoControllerProd implements InfoControllerInterface {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/getPort")
    public String getPort(){
        return port;
    }
}
