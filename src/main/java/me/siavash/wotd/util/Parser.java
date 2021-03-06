package me.siavash.wotd.util;

import me.siavash.wotd.entities.Word;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Parser {

  private String urlBase = "https://www.merriam-webster.com/word-of-the-day/";
  private String date;
  private Document document;
  private List<String> allChildren;

  public static Word get(String date) {
    Parser parser = new Parser(date);
    Word word = new Word();
    word.setDate(parser.getDateStamp());
    word.setTitle(parser.getTitle());
    word.setAttribute(parser.getAttr());
    word.setSyllables(parser.getSyllables());
    word.setDefinition(parser.getDefinition());
    word.setExamples(parser.getExamples());
    word.setDidYouKnow(parser.getDidYouKnow());
    word.setPodcastUrl(parser.getPodcastUrl());
    word.setImageUrl(parser.getImageUrl());
    word.setPronounceUrl(parser.getPronounceUrl());
    return word;
  }

  private Parser(String date) {
    this.date = date;
    this.urlBase += date;
    Elements children;
    try {
      this.document = Jsoup.connect(this.urlBase).get();
      children = this.document.select("div.wod-definition-container").get(0).children();
      children.removeIf(e -> e.text().equals(""));
      this.allChildren = children.stream().map(Element::text).collect(Collectors.toList());
    } catch (IOException e) {
      e.printStackTrace();
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
