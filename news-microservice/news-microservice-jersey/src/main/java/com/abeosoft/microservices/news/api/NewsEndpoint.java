package com.abeosoft.microservices.news.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_XML;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.abeosoft.microservices.news.domain.Story;
import com.abeosoft.microservices.news.domain.StoryList;
import com.abeosoft.microservices.news.repository.StoryRepository;

@Controller
@Path("/stories")
@Produces({ APPLICATION_JSON, APPLICATION_XML })
public class NewsEndpoint {

    private Logger logger = LoggerFactory.getLogger(NewsEndpoint.class);

    @Autowired
    private StoryRepository storyRepository;

    @GET
    public StoryList getStories() {
	List<Story> storiesByAuthor = storyRepository.findAll(new Sort(Sort.Direction.ASC, "author"));
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return new StoryList(storiesByAuthor);
    }

    @GET
    @Path("/id/{id}")
    public Story getStoriesById(@PathParam("id") String id) {
	logger.debug("Get Story by ID: " + id);
	Story storiesByTitle = storyRepository.findOne(id);
	logger.debug("Repository returned count: " + (storiesByTitle != null ? 1 : 0));
	return storiesByTitle;
    }

    @GET
    @Path("/title/{title}")
    public Story getStoriesByTitle(@PathParam("title") String title) {
	Story storiesByTitle = storyRepository.findByTitle(title);
	logger.debug("Repository returned count: " + (storiesByTitle != null ? 1 : 0));
	return storiesByTitle;
    }

    @GET
    @Path("/terms/{terms}")
    public StoryList getStoriesByTerms(@PathParam("terms") String terms) {
	List<Story> storiesByAuthor = storyRepository.findByTitleLikeIgnoreCaseOrderByPublishDateDesc(terms);
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return new StoryList(storiesByAuthor);
    }

    @GET
    @Path("/authors/{author}")
    public List<Story> getStoriesByAuthor(@PathParam("author") String author) {
	List<Story> storiesByAuthor = storyRepository.findByAuthor(author);
	logger.debug("Repository returned count: " + storiesByAuthor.size());
	return storiesByAuthor;
    }

    @GET
    @Path("/sources/{source}")
    public List<Story> getStoriesBySource(@PathParam("source") String source) {
	List<Story> storiesBySource = storyRepository.findBySourceOrderByTitleAsc(source);
	logger.debug("Repository returned count: " + storiesBySource.size());
	return storiesBySource;
    }

    @GET
    @Path("/periodic/daily")
    public List<Story> getDailyStories() {
	List<Story> storiesByPublishDate = storyRepository.getDailyStories();
	logger.debug("Repository returned count: " + storiesByPublishDate.size());
	return storiesByPublishDate;
    }

    @GET
    @Path("/summary/authors/{author}")
    public String countStoriesByAuthor(@PathVariable String author) {
	long count = storyRepository.countByAuthor(author);
	JSONObject json = new JSONObject();
	json.put("author", author);
	json.put("story_count", count);
	return json.toString();
    }
}
