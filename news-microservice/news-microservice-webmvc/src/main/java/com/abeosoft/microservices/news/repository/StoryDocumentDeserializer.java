package com.abeosoft.microservices.news.repository;

import java.time.LocalDateTime;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.abeosoft.microservices.news.domain.Story;
import com.mongodb.DBObject;

@Component
public class StoryDocumentDeserializer implements Converter<DBObject, Story> {

    public Story convert(DBObject source) {
	Story story = new Story();
	story.setId((String) source.get("_id"));
	story.setTitle((String) source.get("title"));
	story.setAuthor((String) source.get("author"));
	story.setArticle((String) source.get("article"));
	story.setPublishDate(LocalDateTime.parse((String) source.get("publishDate")));
	story.setSource((String) source.get("source"));
	return story;
    }

}