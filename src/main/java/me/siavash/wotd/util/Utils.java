package me.siavash.wotd.util;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utils {

  private Utils() {
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


}
