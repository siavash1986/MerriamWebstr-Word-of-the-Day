package me.siavash.wotd.entities;

import lombok.Data;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public final class Word {

  @Id
  @Column(name = "wd_date")
  private String date;

  @Column(name = "wd_title")
  private String title;

  @Column(name = "wd_attribute")
  private String attribute;

  @Column(name = "wd_syllables")
  private String syllables;

  @Column(length = 2000)
  @ElementCollection
  private List<String> definition;

  @Column(length = 2000)
  @ElementCollection
  private List<String> examples;

  @Column(name = "wd_didyouknow", length = 2000)
  private String didYouKnow;

  @Column(name = "wd_podcasturl")
  private String podcastUrl;

  @Column(name = "wd_imageurl")
  private String imageUrl;

  @Column(name = "wd_pronounceUrl")
  private String pronounceUrl;

  public Word() {
  }

}
