package com.ej.sh1rk;

import com.ej.sh1rk.exceptions.Sh1rkParserException;
import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;
import com.ej.sh1rk.parser.XmlParser;
import org.junit.Test;

import static com.ej.sh1rk.XmlDataTest.checkModel;
import static com.ej.sh1rk.XmlDataTest.checkParentModel;

public class XmlParserTest {
    private static final XmlParser XML_PARSER = new XmlParser();

    @Test(expected = IllegalArgumentException.class)
    public void nullData() {
        XML_PARSER.parse(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyData() {
        XML_PARSER.parse("");
    }

    @Test(expected = Sh1rkParserException.class)
    public void parseBadData() {
        XML_PARSER.parse("Bad xml data");
    }

    @Test
    public void parse() {
        Entity entity = XML_PARSER.parse(XmlDataTest.XML_DATA);
        checkModel(entity);
    }

    @Test
    public void parseParent() {
        Entity entity = XML_PARSER.parse(XmlDataTest.XML_DATA_PARENT);
        checkParentModel(entity);
    }

    @Test(expected = Sh1rkParserException.class)
    public void parseNotCloseTag() {
        Entity entity = XML_PARSER.parse(XmlDataTest.XML_DATA_NOT_CLOSE_TAG);
        checkModel(entity);
    }

    @Test(expected = Sh1rkParserException.class)
    public void parseNotOpenTag() {
        Entity entity = XML_PARSER.parse(XmlDataTest.XML_DATA_NOT_OPEN_TAG);
        checkModel(entity);
    }

    @Test(expected = Sh1rkParserException.class)
    public void parseInvalidNameTag() {
        Entity entity = XML_PARSER.parse(XmlDataTest.XML_DATA_INVALID_NAME_TAG);
        checkModel(entity);
    }
}
