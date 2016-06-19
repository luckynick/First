

import org.jsoup.*;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebParser  {
    static void demo() throws Exception
    {
        Document doc = Jsoup.connect("http://www.pravda.com.ua/").get();
//        System.out.println(doc);
        String question = doc.select("body a").text();
        System.out.println("Body:\n: " + question);
    }

}
