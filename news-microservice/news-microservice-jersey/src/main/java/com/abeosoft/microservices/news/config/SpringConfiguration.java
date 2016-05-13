package com.abeosoft.microservices.news.config;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.message.DeflateEncoder;
import org.glassfish.jersey.message.GZipEncoder;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.abeosoft.microservices.news.api.AdminEndpoint;
import com.abeosoft.microservices.news.api.NewsEndpoint;

@Configuration
public class SpringConfiguration {

    @Bean
    public ResourceConfig jerseyConfiguration() {
	ResourceConfig resourceConfig = new ResourceConfig();
	resourceConfig.register(JacksonFeature.class);
	resourceConfig.register(LoggingFilter.class);
	resourceConfig.register(DeflateEncoder.class);
	resourceConfig.register(GZipEncoder.class);
	resourceConfig.register(AdminEndpoint.class);
	resourceConfig.register(NewsEndpoint.class);
	return resourceConfig;
    }
}
