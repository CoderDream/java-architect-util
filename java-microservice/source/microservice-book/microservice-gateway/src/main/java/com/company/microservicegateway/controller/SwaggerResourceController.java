package com.company.microservicegateway.controller;

import com.company.microservicegateway.config.MySwaggerResourceProvider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import springfox.documentation.swagger.web.*;

import java.util.List;

/**
 * Swagger.
 * @author xindaqi
 * @since 2020-11-03
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/swagger-resources")
public class SwaggerResourceController {

    private MySwaggerResourceProvider swaggerResourceProvider;

    @Autowired 
    public SwaggerResourceController(MySwaggerResourceProvider swaggerResourceProvider) {
        this.swaggerResourceProvider = swaggerResourceProvider;
    }

    @RequestMapping(value = "/configuration/security")
    public ResponseEntity<SecurityConfiguration> securityConfiguration() {
        return new ResponseEntity<>(SecurityConfigurationBuilder.builder().build(), HttpStatus.OK);
    }
 
    @RequestMapping(value = "/configuration/ui")
    public ResponseEntity<UiConfiguration> uiConfiguration() {
        return new ResponseEntity<>(UiConfigurationBuilder.builder().build(), HttpStatus.OK);
    }
 
    @RequestMapping
    public ResponseEntity<List<SwaggerResource>> swaggerResources() {
        return new ResponseEntity<>(swaggerResourceProvider.get(), HttpStatus.OK);
    }

    
}