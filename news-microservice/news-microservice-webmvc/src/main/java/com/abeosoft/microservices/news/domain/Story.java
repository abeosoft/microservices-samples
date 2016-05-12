package com.abeosoft.microservices.news.domain;

import java.time.LocalDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "story")
public class Story {

    @Id
    @XmlElement(name = "id")
    private String id;

    @XmlElement(name = "title")
    private String title;

    @XmlElement(name = "source")
    private String source;

    @XmlElement(name = "publish_date")
    private LocalDateTime publishDate;

    @XmlElement(name = "author")
    private String author;

    @XmlElement(name = "article")
    private String article;

    public Story() {
	super();
    }

    public Story(String title, LocalDateTime publishDate, String author, String article) {
	this.source = "WEB";
	this.title = title;
	this.publishDate = publishDate;
	this.author = author;
	this.article = article;
    }

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getSource() {
	return source;
    }

    public void setSource(String source) {
	this.source = source;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public LocalDateTime getPublishDate() {
	return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
	this.publishDate = publishDate;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getArticle() {
	return article;
    }

    public void setArticle(String article) {
	this.article = article;
    }

    // // @JsonFormat(shape = Shape.STRING, pattern = "MM-dd-YYYY")
    // public Date getPublishDateLegacy() {
    // return Date.from(publishDate.atZone(ZoneId.systemDefault()).toInstant());
    // }
}
