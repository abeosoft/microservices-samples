package com.abeosoft.microservices.news.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import com.abeosoft.microservices.news.domain.Story;

@NoRepositoryBean
public interface StoryRepositoryCustom {

    List<Story> getDailyStories();

}
