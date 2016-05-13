package com.abeosoft.microservices.news.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;

@Controller
@Path("/")
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class AdminEndpoint {

    @Autowired
    private Environment env;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GET
    @Path("/health")
    public String healthcheck() {
	JSONObject json = new JSONObject();
	json.put("service", env.getProperty("spring.application.name"));
	json.put("instance_id", env.getProperty("spring.cloud.consul.discovery.instance-id"));
	json.put("status", "UP");
	return json.toString();
    }

    @GET
    @Path("/whoami")
    public ServiceInstance me() {
	return discoveryClient.getLocalServiceInstance();
    }

    @GET
    @Path("/env/property")
    public String property(@PathParam("key") String property) {
	return env.getProperty(property, "Not Found");
    }

}
