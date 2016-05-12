package com.abeosoft.microservices.news.config;

import java.net.UnknownHostException;

import javax.annotation.PreDestroy;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.abeosoft.microservices.news.util.LocalDateTimeCodec;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Configuration
public class MongoConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(MongoConfiguration.class);

    @Autowired
    private MongoProperties properties;

    @Autowired
    private Environment environment;

    private MongoClient mongo;

    @Bean
    public MongoClientOptions mongoClientOptions() {
	logger.debug("Configuring Mongo Client with Local Date Time Codec Support.");
	LocalDateTimeCodec myCodec = new LocalDateTimeCodec();
	CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
		CodecRegistries.fromCodecs(myCodec));
	return MongoClientOptions.builder().codecRegistry(codecRegistry).build();
    }

    @PreDestroy
    public void close() {
	if (this.mongo != null) {
	    this.mongo.close();
	}
    }

    @Bean
    public MongoClient mongo() throws UnknownHostException {
	this.mongo = properties.createMongoClient(mongoClientOptions(), environment);
	return mongo;
    }

}
