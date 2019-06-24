package me.siavash.wotd.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
