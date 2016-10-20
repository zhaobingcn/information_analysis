package com.isa.analysis.sdn.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import org.neo4j.ogm.annotation.*;

import java.util.List;

/**
 * Created by hexu on 2016/10/19.
 */
@JsonIdentityInfo(generator = JSOGGenerator.class)
@NodeEntity(label = "Paper")
public class Paper {

    @GraphId
    private Long id;
    @Property(name = "title")
    private String title;
    @Property(name = "link")
    private String link;
    @Property(name = "quote")
    private int quote;
    @Property(name = "date")
    private String date;

    @Relationship(type = "publish", direction = Relationship.INCOMING)
    private List<Author> authors;

    @Relationship(type = "involve", direction = Relationship.OUTGOING)
    private List<Keyword> keywords;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}
