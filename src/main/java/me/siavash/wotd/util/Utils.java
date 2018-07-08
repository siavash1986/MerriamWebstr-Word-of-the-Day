package me.siavash.wotd.util;

import me.siavash.wotd.entities.FlatWord;
import me.siavash.wotd.entities.Word;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Utils {

  private Utils() {
  }

  public static boolean validate(String date) {
    if (date.equals("today")) {
      return true;
    }
    try {
      LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
      return validDateRange(localDate);

    } catch (DateTimeParseException e) {
      return false;
    }
  }

  public static boolean validate(String begin, String end) {
    return validate(begin) && validate(end)
        && !LocalDate.parse(parseDate(begin)).isAfter(LocalDate.parse(parseDate(end)));
  }

  private static boolean validDateRange(LocalDate localDate) {
    return (!(localDate.isBefore(LocalDate.of(2006, 8, 31)) ||
        localDate.isAfter(LocalDate.now().plusDays(1))));
  }

  public static String parseDate(String date) {
    return date.equals("today") ? LocalDate.now().format(DateTimeFormatter.ISO_DATE)
        : date;
  }

  public static FlatWord flatWordAdapter(Word w) {
    FlatWord flatWord = new FlatWord();
    flatWord.setDate(w.getDate());
    flatWord.setTitle(w.getTitle() != null ? w.getTitle() : "");
    flatWord.setAttribute(w.getAttribute() != null ? w.getAttribute() : "");
    flatWord.setSyllables(w.getSyllables() != null ? w.getSyllables() : "");
    flatWord.setDefinition(w.getDefinition() != null ? w.getDefinition().stream().collect(Collectors.joining(System.getProperty("line.separator"))) : "");
    flatWord.setExamples(w.getExamples() != null ? w.getExamples().stream().collect(Collectors.joining(System.getProperty("line.separator"))) : "");
    flatWord.setDidYouKnow(w.getDidYouKnow() != null ? w.getDidYouKnow() : "");
    flatWord.setPodcastUrl(w.getPodcastUrl() != null ? w.getPodcastUrl() : "");
    flatWord.setImageUrl(w.getImageUrl() != null ? w.getImageUrl() : "");
    flatWord.setPronounceUrl(w.getPronounceUrl() != null ? w.getPronounceUrl() : "");
    return flatWord;
  }

  public static String getResponse(String url) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
        .url(url)
        .build();
    String xml = "";

    try {
      Response response = client.newCall(request).execute();
      xml = response.body() != null ? response.body().string() : "";
    } catch (IOException | NullPointerException e) {
      e.printStackTrace();

    }
    return xml;
  }

  public static String getSoundElementFromXML(String xml) {

    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    org.w3c.dom.Document doc = null;

    try {
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      InputSource is = new InputSource(new StringReader(xml));
      doc = dBuilder.parse(is);
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
      return "";
    }

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("entry");

    for (int i = 0; i < nList.getLength(); i++) {
      Node nNode;
      try {
        nNode = nList.item(i).getFirstChild();
        for (; nNode != null && !nNode.toString().contains("sound"); nNode = nNode.getNextSibling()) ;

        if (nNode == null) continue;
        else {
/*      <sound>
            <wav>hypocr02.wav</wav>
        </sound>
*/
          String wav = nNode.getFirstChild().getFirstChild().getTextContent();
          if (wav == null) {
            continue;
          }else {
            return String.format("https://media.merriam-webster.com/soundc11/%c/%s", wav.charAt(0), wav);
          }
        }

      } catch (Exception e) {
        System.out.println(String.format("Exception %s thrown at Utils.getSoundElementFromXML", e.getClass()));
        Logger.getGlobal().log(Level.WARNING, String.format("Exception %s thrown at Utils.getSoundElementFromXML", e.getClass()));
        continue;
      }
    }

    return "";
  }

  public static String getPronounceUrl(Word word) {
    String url = "https://www.dictionaryapi.com/api/v1/references/collegiate/xml/" + word.getTitle() + "?key=" + "33c5f056-ddb9-40a3-bc30-2de932b7a26d";

    String xml = Utils.getResponse(url);
    return xml.equals("") ? "" : Utils.getSoundElementFromXML(xml);

  }

  public static void downloadPodcast(String date) {

    String podcastUrl = "https://www.merriam-webster.com/wotd/feed/rss2";
    String xml = getResponse(podcastUrl);
    System.out.println("xml = " + xml);




    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    org.w3c.dom.Document doc = null;

    try {
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      InputSource is = new InputSource(new StringReader(xml));
      doc = dBuilder.parse(is);
    } catch (ParserConfigurationException | IOException | SAXException e) {
      e.printStackTrace();
    }

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("item");
    System.out.println();

  }
}
