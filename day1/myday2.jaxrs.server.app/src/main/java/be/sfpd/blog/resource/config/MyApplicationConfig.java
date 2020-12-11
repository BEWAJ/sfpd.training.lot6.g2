package be.sfpd.blog.resource.config;

import be.sfpd.blog.resource.provider.AuthenticationFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class MyApplicationConfig extends ResourceConfig {

    public MyApplicationConfig() {
        packages("io.swagger.v3.jaxrs2.integration.resources,be.sfpd.blog");
        register(AuthenticationFilter.class);
    }
}