package com.company.microservicegateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.*;

/**
 * Aggregate swagger.
 * @author xindaqi
 * @since 2020-11-03
 */
@Component
public class MySwaggerResourceProvider implements SwaggerResourcesProvider {

    private static final String SWAGGER2URL = "/v2/api-docs";

    private final RouteLocator routeLocator;

    @Value("${spring.application.name}")
    private String self;

    @Autowired 
    public MySwaggerResourceProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }

    @Override 
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<String> routeHosts = new ArrayList<>();
        routeLocator.getRoutes().filter(route -> route.getUri().getHost() != null)
                    .filter(route -> !self.equals(route.getUri().getHost()))
                    .subscribe(route -> routeHosts.add(route.getUri().getHost()));

        Set<String> dealed = new HashSet<>();
        routeHosts.forEach(instance -> {
            String url = "/" + instance.toLowerCase() + SWAGGER2URL;
            if(!dealed.contains(url)) {
                dealed.add(url);
                SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setUrl(url);
                swaggerResource.setName(instance);
                resources.add(swaggerResource);
            }
        });
        return resources;
    }
    
}