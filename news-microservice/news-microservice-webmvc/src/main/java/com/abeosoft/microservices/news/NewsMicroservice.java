package com.abeosoft.microservices.news;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.abeosoft.microservices.news.config.DataRepositoriesConfiguration;
import com.abeosoft.microservices.news.config.MongoConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@ComponentScan
@EnableAutoConfiguration(exclude = MongoAutoConfiguration.class)
@Import({ MongoConfiguration.class, DataRepositoriesConfiguration.class })
public class NewsMicroservice {

    private static final Logger logger = LoggerFactory.getLogger(NewsMicroservice.class);

    public static void main(String[] args) {
	ConfigurableApplicationContext context = SpringApplication.run(NewsMicroservice.class, args);
	ObjectMapper bean = context.getBean(ObjectMapper.class);
	logger.debug("Object Mapper: " + bean);
    }

}
