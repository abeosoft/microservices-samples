package com.abeosoft.microservices.news.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class SpringJacksonConfiguration {

    // @Bean
    // public ObjectMapper objectMapper(ObjectMapper mapper) {
    // mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    // mapper.setSerializationInclusion(Include.NON_NULL);
    // mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    //
    // mapper.registerModule(new JavaTimeModule());
    //
    // mapper.setAnnotationIntrospector(AnnotationIntrospector.pair(new
    // JacksonAnnotationIntrospector(),
    // new JaxbAnnotationIntrospector(mapper.getTypeFactory())));
    // return mapper;
    // }

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
	Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	builder.serializationInclusion(Include.NON_NULL);
	builder.failOnUnknownProperties(false);
	builder.modules(new JavaTimeModule());
	return builder;
    }
}
