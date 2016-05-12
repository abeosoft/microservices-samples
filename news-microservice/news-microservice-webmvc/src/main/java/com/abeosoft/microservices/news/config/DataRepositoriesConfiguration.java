package com.abeosoft.microservices.news.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.abeosoft.microservices.news.repository.StoryDocumentDeserializer;
import com.abeosoft.microservices.news.repository.StoryDocumentSerializer;

@Configuration
@EnableMongoRepositories(basePackages = "com.abeosoft.microservices.news.repository")
public class DataRepositoriesConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DataRepositoriesConfiguration.class);

    @Bean
    public CustomConversions customConversions() {
	List<Converter<?, ?>> converters = new ArrayList<>();
	converters.add(new StoryDocumentSerializer());
	converters.add(new StoryDocumentDeserializer());
	return new CustomConversions(converters);
    }

}
