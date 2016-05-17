package com.abeosoft.microservices.news.repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.mongodb.core.MongoOperations;

import com.abeosoft.microservices.news.domain.Story;

public class StoryRepositoryImpl implements StoryRepositoryCustom {

    @Inject
    private MongoOperations mongoOperations;

    @Override
    // @org.springframework.data.mongodb.repository.Query("{"created_at":{$gt:ISODate("2013-04-30T00:00:00Z"),$lt:ISODate("2013-04-30T23:59:59Z")}}")
    public List<Story> getDailyStories() {
	LocalDateTime startOfDay = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
	LocalDateTime endOfDay = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
	return mongoOperations.find(query(where("publishDate").gte(startOfDay).lte(endOfDay)), Story.class);
    }
}
