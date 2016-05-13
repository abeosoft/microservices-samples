package com.abeosoft.microservices.news.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement(name = "story_list")
@XmlAccessorType(XmlAccessType.FIELD)
public class StoryList {

    @XmlElement(name = "story")
    @JsonProperty(value = "stories")
    private List<Story> stories;

    public StoryList() {
	super();
    }

    public StoryList(List<Story> stories) {
	this.stories = stories;
    }

    public List<Story> getStories() {
	return stories;
    }

    public void setStories(List<Story> stories) {
	this.stories = stories;
    }
}
