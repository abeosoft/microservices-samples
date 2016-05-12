package com.abeosoft.microservices.news.repository;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.abeosoft.microservices.news.domain.Story;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Component
public class StoryDocumentSerializer implements Converter<Story, DBObject> {

    public DBObject convert(Story source) {
	DBObject dbo = new BasicDBObject();
	dbo.put("_id", source.getId());
	dbo.put("title", source.getTitle());
	dbo.put("author", source.getAuthor());
	dbo.put("article", source.getArticle());
	dbo.put("publishDate", source.getPublishDate());
	dbo.put("source", source.getSource());
	dbo.removeField("_class");
	return dbo;
    }

}