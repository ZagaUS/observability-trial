package org.zaga;

import java.io.IOException;

import org.eclipse.microprofile.config.inject.ConfigProperties;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
// import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Response;

@ConfigProperties(prefix = "quarkus.http.cors")
public class CorsFilter implements ContainerRequestFilter {
     @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    requestContext.getHeaders().add("Access-Control-Allow-Origin", "*");
    requestContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
    requestContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
    requestContext.getHeaders().add("Access-Control-Allow-Methods", "POST, PUT, DELETE, OPTIONS, HEAD");

      if (requestContext.getMethod().equals("OPTIONS")) {
            requestContext.abortWith(Response.ok().build());
        }
}
}
