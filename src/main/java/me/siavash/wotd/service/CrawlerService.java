package me.siavash.wotd.service;


import lombok.extern.slf4j.Slf4j;
import me.siavash.wotd.entities.Word;
import me.siavash.wotd.repositories.WordRepository;
import me.siavash.wotd.util.Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CrawlerService {

  private String baseUrl;
  private WordRepository repository;
  private String date;
  private Document document;
  private List<String> allChildren;

  public CrawlerService(WordRepository repository,
                        @Value("${application.properties.crawler.baseUrl}") String baseUrl){
    this.repository = repository;
    this.baseUrl = baseUrl;
  }

  @Scheduled(cron = "* 0 1 * * *")
  public void update(){
    retrievePodcastForDate(LocalDate.now().toString());
  }

  public void retrievePodcastForDate(String date) {
    readTheWebPage(date);
    Word word = Word.builder()
        .title(getTitle())
        .date(getDateStamp())
        .attribute(getAttr())
        .syllables(getSyllables())
        .definition(getDefinition())
        .examples(getExamples())
        .didYouKnow(getDidYouKnow())
        .podcastUrl(getPodcastUrl())
        .imageUrl(getImageUrl())
        .pronounceUrl(getPronounceUrl())
        .build();
    repository.save(word);
  }


  private void readTheWebPage(String date) {
    this.date = date;
    this.baseUrl += date;
    Elements children;
    try {
      this.document = Jsoup.connect(this.baseUrl).get();
      children = this.document.select("div.wod-definition-container").get(0).children();
      children.removeIf(e -> e.text().equals(""));
      this.allChildren = children.stream().map(Element::text).collect(Collectors.toList());
    } catch (IOException e) {
      log.error("Something went wrong while crawling the web page. ", e);
    }
  }

  private String getTitle() {
    String s = "div.quick-def-box h1";
    Element select = this.document.select(s).get(0);
    return select != null ? select.text() : null;
  }

  private String getAttr() {
    String s = "div.word-attributes span.main-attr";
    Element select = this.document.select(s).get(0);
    return select != null ? select.text() : null;
  }

  private String getSyllables() {
    String s = "div.word-attributes span.word-syllables";
    Element select = this.document.select(s).get(0);
    return select != null ? select.text() : null;
  }

  private List<String> getDefinition() {

    List<String> defList = new ArrayList<>();

    boolean hasDefinition = this.allChildren.contains("Did You Know?");
    int indexOfDidYouKnow = hasDefinition ? this.allChildren.indexOf("Did You Know?") : this.allChildren.size();
    for (int i = 0; i < indexOfDidYouKnow; i++) {
      defList.add(this.allChildren.get(i));
    }
    defList.removeIf(s -> s.equals("Definition"));
    return defList;
  }

  private List<String> getExamples() {

    List<String> explList = new ArrayList<>();

    String s = "div.wotd-examples p";
    Elements select = this.document.select(s);
    for (Element element : select) {
      if (element != null) {
        explList.add(element.text());
      }
    }

    return explList;
  }

  private String getDidYouKnow() {

    List<String> list = new ArrayList<>();

    String s = "div.did-you-know-wrapper div.left-content-box p";
    Elements select = this.document.select(s);
    for (Element element : select) {
      if (element != null) {
        list.add(element.text());
      }
    }
    return list.stream().collect(Collectors.joining(System.getProperty("line.separator")));
  }

  private String getDateStamp() {
    return this.date;
  }

  private String getPodcastUrl() {
    String base = "https://s3.us-east-2.amazonaws.com/mwwottd/words/";
    return base + "wd" + this.date.replace("-", "") + ".mp3";
  }

  private String getImageUrl() {
    return null;
  }

  private String getPronounceUrl() {
    String url = "https://www.dictionaryapi.com/api/v1/references/collegiate/xml/" + getTitle() + "?key=" + "33c5f056-ddb9-40a3-bc30-2de932b7a26d";

    String xml = Utils.getResponse(url);
    return xml.equals("") ? "" : Utils.getSoundElementFromXML(xml);
  }


}
