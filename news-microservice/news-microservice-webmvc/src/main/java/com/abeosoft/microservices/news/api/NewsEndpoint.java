package com.abeosoft.microservices.news.api;

import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abeosoft.microservices.news.domain.Story;
import com.abeosoft.microservices.news.domain.StoryList;
import com.abeosoft.microservices.news.repository.StoryRepository;

@RestController
@RequestMapping(value = "/stories", produces = { "application/json", "application/xml" })
public class NewsEndpoint {

    private Logger logger = LoggerFactory.getLogger(NewsEndpoint.class);

    @Autowired
    private StoryRepository storyRepository;

    @RequestMapping(method = RequestMethod.GET)
    public StoryList getStories() {
	List<Story> storiesByAuthor = storyRepository.findAll(new Sort(Sort.Direction.ASC, "author"));
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return new StoryList(storiesByAuthor);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public Story getStoriesById(@PathVariable String id) {
	logger.debug("Get Story by ID: " + id);
	Story storiesByTitle = storyRepository.findOne(id);
	logger.debug("Repository returned count: " + (storiesByTitle != null ? 1 : 0));
	return storiesByTitle;
    }

    @RequestMapping(value = "/title/{title}", method = RequestMethod.GET)
    public Story getStoriesByTitle(@PathVariable String title) {
	Story storiesByTitle = storyRepository.findByTitle(title);
	logger.debug("Repository returned count: " + (storiesByTitle != null ? 1 : 0));
	return storiesByTitle;
    }

    @RequestMapping(value = "/terms/{terms}", method = RequestMethod.GET)
    public StoryList getStoriesByTerms(@PathVariable String terms) {
	List<Story> storiesByAuthor = storyRepository.findByTitleLikeIgnoreCaseOrderByPublishDateDesc(terms);
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return new StoryList(storiesByAuthor);
    }

    @RequestMapping(value = "/authors/{author}", method = RequestMethod.GET)
    public List<Story> getStoriesByAuthor(@PathVariable String author) {
	List<Story> storiesByAuthor = storyRepository.findByAuthor(author);
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return storiesByAuthor;
    }

    @RequestMapping(value = "/sources/{source}", method = RequestMethod.GET)
    public List<Story> getStoriesBySource(@PathVariable String source) {
	List<Story> storiesBySource = storyRepository.findBySourceOrderByTitleAsc(source);
	logger.debug("Repository returned count: " + storiesBySource.size());
	return storiesBySource;
    }

    @RequestMapping(value = "/periodic/daily", method = RequestMethod.GET)
    public List<Story> getDailyStories() {
	List<Story> storiesByPublishDate = storyRepository.getDailyStories();
	logger.debug("Repository returned count: " + storiesByPublishDate.size());
	return storiesByPublishDate;
    }

    @RequestMapping(value = "/summary/authors/{author}", method = RequestMethod.GET)
    public String countStoriesByAuthor(@PathVariable String author) {
	long count = storyRepository.countByAuthor(author);
	JSONObject json = new JSONObject();
	json.put("author", author);
	json.put("story_count", count);
	return json.toString();
    }
}
