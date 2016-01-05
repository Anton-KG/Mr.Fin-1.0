package com.ej.sh1rk.validator;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class {@code XmlValidator} is responsible for implementation .xml files validation.
 *
 */
public final class XmlValidator extends Validator {

    private String hasHeader;

    /**
     * Default constructor for creating {@code XmlValidator} object.
     */
    public XmlValidator() {
        saxParser();
        hasHeader();
        lineErrorNum();
        thePercentageOfCompletion();
        lineCounter();
        fileSize();
    }

    /**
     * This method allow to get {@code XmlValidator} object. Then uses this object
     * as parameter for creating {@link PrintOut} object. Then make output into console.
     */
    public static void getXmlValidator() {
        Validator valObj = new XmlValidator();
        PrintOut printOut = new PrintOut(valObj);
        printOut.chooseOutput();
    }

    private void saxParser() {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser;
        try {
            saxParser = parserFactory.newSAXParser();
            saxParser.parse(SourceReader.getFile(), new DefaultHandler());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            setLineException(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void hasHeader() {
        String xmlHeader = "<?xml";
        String temp;
        ArrayList<String> words = new ArrayList<>();
        StringTokenizer stringTokenizer = new StringTokenizer(
                SourceReader.readFileToString());
        while (stringTokenizer.hasMoreTokens()) {
            temp = stringTokenizer.nextToken();
            words.add(temp);
        }
        for (int i = 0; i < words.size(); i++) {
            temp = words.get(i);
            if (temp.equalsIgnoreCase(xmlHeader)) {
                hasHeader = "enabled";
                break;
            } else {
                hasHeader = "absent";
                break;
            }
        }
    }

    /**
     * This method return information abut header`s availability.
     *
     * @return information abut header`s availability
     */
    public String getHasHeader() {
        return hasHeader;
    }
}

