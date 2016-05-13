package com.abeosoft.microservices.news;

import static org.springframework.boot.Banner.Mode.OFF;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class NewsMicroservice {

    private static final Logger logger = LoggerFactory.getLogger(NewsMicroservice.class);

    public static void main(String[] args) {
	ConfigurableApplicationContext context = new SpringApplicationBuilder().bannerMode(OFF).web(true)
		.sources(NewsMicroservice.class).run(args);
	logger.debug("Loaded application successfully - context: " + context);
    }

}
