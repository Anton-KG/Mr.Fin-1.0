package com.ej.sh1rk.parser;

import com.ej.sh1rk.Entity;
import com.ej.sh1rk.exceptions.Sh1rkParserException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.StringReader;

/**
 * The <code>XmlParser</code> class parses a string containing the data in xml format.
 */
public class XmlParser implements Parser {
    /**
     * Parse of the contents of the passed parameter.
     *
     * @param data A string containing the content to be parsed in xml format.
     * @return {@link com.ej.sh1rk.Entity Entity}
     */
    public Entity parse(final String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("String to parse 'data' cannot be null or empty");
        }

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            StringReader reader = new StringReader(data);
            InputSource inputSource = new InputSource(reader);
            XmlHandler xmlHandler = new XmlHandler();

            parser.parse(inputSource, xmlHandler);

            Entity entity = new Entity(null, xmlHandler.getItems());
            entity.setParent(xmlHandler.getParent());
            return entity;
        } catch (IOException e) {
            throw new Sh1rkParserException("Exception of I/O while parsing: " + e.toString(), e);
        } catch (SAXException e) {
            throw new Sh1rkParserException("Exception while parsing: " + e.toString(), e);
        } catch (ParserConfigurationException e) {
            throw new Sh1rkParserException("A serious configuration error: " + e.toString(), e);
        }
    }
}
