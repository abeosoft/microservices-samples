package com.abeosoft.microservices.news.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.abeosoft.microservices.news.domain.Story;

public interface StoryRepository extends MongoRepository<Story, String>, StoryRepositoryCustom {

    public List<Story> findByTitleLikeIgnoreCaseOrderByPublishDateDesc(String title);

    public Story findByTitle(String title);

    public List<Story> findBySourceOrderByTitleAsc(String source);

    public List<Story> findByAuthor(String author);

    public long countByAuthor(String author);
}
