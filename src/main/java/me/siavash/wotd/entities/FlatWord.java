package me.siavash.wotd.entities;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;


public final class FlatWord implements Serializable {

  private String date;

  private String title;

  private String attribute;

  private String syllables;

  private String definition;

  private String examples;

  private String didYouKnow;

  private String podcastUrl;

  private String imageUrl;

  public FlatWord() {
  }

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

  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public String getExamples() {
    return examples;
  }

  public void setExamples(String examples) {
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

  @Override
  public boolean equals(Object obj) {
    return obj.getClass() == getClass() && EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this);
  }
}
