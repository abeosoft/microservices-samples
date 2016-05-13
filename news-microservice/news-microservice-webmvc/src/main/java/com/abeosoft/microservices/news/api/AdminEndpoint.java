package com.abeosoft.microservices.news.api;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", method = RequestMethod.GET, produces = { "application/json", "application/xml" })
public class AdminEndpoint {

    @Autowired
    private Environment env;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/health")
    public String healthcheck() {
	JSONObject json = new JSONObject();
	json.put("service", env.getProperty("spring.application.name"));
	json.put("instance_id", env.getProperty("spring.cloud.consul.discovery.instance-id"));
	json.put("status", "UP");
	return json.toString();
    }

    @RequestMapping(value = "/whoami")
    public ServiceInstance me() {
	return discoveryClient.getLocalServiceInstance();
    }

    @RequestMapping(value = "/env/property")
    public String property(@PathVariable String property) {
	return env.getProperty(property, "Not Found");
    }

}
