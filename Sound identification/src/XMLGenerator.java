import javafx.util.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class XMLGenerator
{
    final static int SYMBOLS = 128;
    public static void main(String args[])
    {
        Document doc = generateBindings(new int[]{(char)0, (char)5}, new Pair<Integer, Integer>(200, 2000));
        try
        {
            writeXML("bindings.xml", doc);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void writeXML(String path, Document doc) throws Exception
    {
        Transformer t = TransformerFactory.newInstance().newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        t.transform(new DOMSource(doc), new StreamResult(new FileOutputStream(path)));
    }

    public static Document generateBindings(int[] excludedSymbols, Pair<Integer, Integer> ... borders)
    {
        int symbolsToDo = SYMBOLS - excludedSymbols.length;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        List symbols = new ArrayList<Element>();
        List frequencies = new ArrayList<Element>();
        try
        {
            db = dbf.newDocumentBuilder();
        }
        catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        }
        Document doc = db.newDocument();
        Element rootElement = doc.createElement("resources");
        Element symbolsArray = doc.createElement("string-array");
        symbolsArray.setAttribute("name", "symbols");
        for(int i = 0; i < symbolsToDo; i++)
        {
            Element record = doc.createElement("item");
            record.setTextContent(String.valueOf(i));
            symbols.add(record);
        }
        Element frequenciesArray = doc.createElement("string-array");
        frequenciesArray.setAttribute("name", "frequencies");
        int allowedFreqNum = 0;
        for(Pair p : borders)
        {
            allowedFreqNum += (Integer)p.getValue() - (Integer)p.getKey();
        }
        double step = allowedFreqNum/(double)symbolsToDo; //use of double may be redundant here
        for(int i = 0, j = 0; i < borders.length; i++)
        {
            double freq = borders[i].getKey();
            for(; freq < borders[i].getValue() && j < symbolsToDo; freq += step, j++)
            {
                boolean excluded = false;
                for(int val : excludedSymbols)
                {
                    if(j == val) excluded = true;
                }
                int freqFin;
                if(excluded)
                {
                    freqFin = -1;
                    freq -= step;
                }
                else freqFin = (int) freq;
                Element record = doc.createElement("item");
                record.setTextContent(String.valueOf(freqFin));
                frequencies.add(record);
            }
        }
        doc.appendChild(rootElement);
        rootElement.appendChild(symbolsArray);
        rootElement.appendChild(frequenciesArray);
        for(Object e : symbols)
        {
            symbolsArray.appendChild((Element)e);
        }
        for(Object e : frequencies)
        {
            frequenciesArray.appendChild((Element)e);
        }
        return doc;
    }
}
