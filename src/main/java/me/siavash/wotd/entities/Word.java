package me.siavash.wotd.entities;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
public final class Word implements Serializable{

    @Id
    @Column(name = "wd_date")
    private String date;

    @Column (name = "wd_title")
    private String title;

    @Column (name = "wd_attribute")
    private String attribute;

    @Column (name = "wd_syllables")
    private String syllables;

    @Column (length = 2000)
    @ElementCollection
    private List<String> definition;

    @Column (length = 2000)
    @ElementCollection
    private List<String> examples;

    @Column (name = "wd_didyouknow", length = 2000)
    private String didYouKnow;

    @Column (name = "wd_podcasturl")
    private String podcastUrl;

    @Column (name = "wd_imageurl")
    private String imageUrl;

    public Word(){}

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public String getSyllables() {
        return syllables;
    }

    public void setSyllables(String syllables) {
        this.syllables = syllables;
    }

    public List<String> getDefinition() {
        return definition;
    }

    public void setDefinition(List<String> definition) {
        this.definition = definition;
    }

    public List<String> getExamples() {
        return examples;
    }

    public void setExamples(List<String> examples) {
        this.examples = examples;
    }

    public String getDidYouKnow() {
        return didYouKnow;
    }

    public void setDidYouKnow(String didYouKnow) {
        this.didYouKnow = didYouKnow;
    }

    public String getPodcastUrl() {
        return podcastUrl;
    }

    public void setPodcastUrl(String podcastUrl) {
        this.podcastUrl = podcastUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "WordoftheDay{" +
                ", date= '" + date +
                ", title= '" + title + '\'' +
                ", attribute= '" + attribute + '\'' +
                ", syllables= '" + syllables + '\'' +
                ", definition= " + definition +
                ", examples= " + examples +
                ", didYouKnow= '" + didYouKnow + '\'' +
                ", podcastUrl= '" + podcastUrl + '\'' +
                ", imageUrl= '" + imageUrl + '\'' +
                '}';
    }
}
