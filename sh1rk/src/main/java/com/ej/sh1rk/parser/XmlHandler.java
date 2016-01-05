package com.ej.sh1rk.parser;

import com.ej.sh1rk.data.Item;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

/**
 * The <code>XmlHandler</code> class for SAX2 event handlers.
 * Receives a notices from the SAX parser and passes them
 * the object of class ModelBuilder for construct an object of class Model.
 */
public class XmlHandler extends DefaultHandler {
    private final ItemsBuilder builder = new ItemsBuilder();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        builder.openTag(qName);

        for (int i = 0; i < attributes.getLength(); i++) {
            String name = attributes.getQName(i);
            String value = attributes.getValue(i);

            builder.setAttribute(name, value);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        int begin = start;
        int end = start + length;

        while ((begin < end) && (ch[begin] == ' ')) {
            begin++;
        }
        while ((begin < end) && (ch[end - 1] == ' ')) {
            end--;
        }

        if (begin != end) {
            builder.setElementValue(new String(ch, begin, end - begin));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        builder.closeTag(qName);
    }

    /**
     * Returned list of items of type {@code Item}.
     *
     * @return list of items of type {@code Item}
     */
    public List<Item> getItems() {
        return builder.getItems();
    }

    /**
     * Get parent to item(s).
     *
     * @return Name of parent item(s).
     */
    public String getParent() {
        return builder.getParent();
    }
}
