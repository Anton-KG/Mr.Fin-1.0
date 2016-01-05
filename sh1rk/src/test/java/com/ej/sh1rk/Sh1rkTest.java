package com.ej.sh1rk;

import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkIncorrectFormatException;
import com.ej.sh1rk.transformer.Header;
import org.junit.Test;

import static org.junit.Assert.*;


public class Sh1rkTest {
    @Test(expected = IllegalArgumentException.class)
    public void nullData() {
        Sh1rk sh1rk = Sh1rk.scan(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void emptyData() {
        Sh1rk sh1rk = Sh1rk.scan("");
    }

    @Test(expected = Sh1rkIncorrectFormatException.class)
    public void parseBadData() {
        Sh1rk sh1rk = Sh1rk.scan("Bad xml data");
    }

    @Test
    public void scanXml() {
        Sh1rk sh1rk = Sh1rk.scan(Sh1rkDataTest.XML_DATA);
        Item book = sh1rk.getItem("book");
        assertNotNull(book);

        Item other = sh1rk.getItem("other");
        assertNull(other);
    }

    @Test
    public void scanJson() {
        Sh1rk sh1rk = Sh1rk.scan(Sh1rkDataTest.JSON_DATA);
        Item book = sh1rk.getItem("book");
        assertNotNull(book);

        Item other = sh1rk.getItem("other");
        assertNull(other);
    }

    @Test
    public void createXml() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                .with("author", "John Stevens")
                .with("genre", "Winter Chronicles"));

        String xml = sh1rk
                .toXML()
                .build();

        assertEquals(xml, Sh1rkDataTest.XML_DATA);

        String xmlPretty = sh1rk
                .toXML()
                .prettyPrint()
                .build();

        assertEquals(xmlPretty, Sh1rkDataTest.XML_DATA_PRETTY);
    }

    @Test
    public void createXmlWithXmlHeader() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                .with("author", "John Stevens")
                .with("genre", "Winter Chronicles"));

        String xml = sh1rk
                .toXML()
                .withXmlHeader(Header.STANDART)
                .build();

        assertEquals(xml, Sh1rkDataTest.XML_DATA_WITH_HEADER);

        String xmlPretty = sh1rk
                .toXML()
                .withXmlHeader(Header.STANDART)
                .prettyPrint()
                .build();

        assertEquals(xmlPretty, Sh1rkDataTest.XML_DATA_PRETTY_WITH_HEADER);
    }

    @Test
    public void createXmlTwoItem() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles"),
                new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles")
        );

        String xml = sh1rk
                .toXML()
                .build();

        assertEquals(xml, Sh1rkDataTest.XML_DATA_TWO_ITEM);

        String xmlPretty = sh1rk
                .toXML()
                .prettyPrint()
                .build();

        assertEquals(xmlPretty, Sh1rkDataTest.XML_DATA_PRETTY_TWO_ITEM);
    }

    @Test
    public void createXmlTwoItemWithHeader() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles"),
                new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles")
        );

        String xml = sh1rk
                .toXML()
                .withXmlHeader(Header.STANDART)
                .build();

        assertEquals(xml, Sh1rkDataTest.XML_DATA_TWO_ITEM_WITH_HEADER);

        String xmlPretty = sh1rk
                .toXML()
                .withXmlHeader(Header.STANDART)
                .prettyPrint()
                .build();

        assertEquals(xmlPretty, Sh1rkDataTest.XML_DATA_PRETTY_TWO_ITEM_WITH_HEADER);
    }

    @Test
    public void createJson() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                .with("author", "John Stevens")
                .with("genre", "Winter Chronicles"));

        String json = sh1rk
                .toJSON()
                .build();

        assertEquals(json, Sh1rkDataTest.JSON_DATA);

        String jsonPretty = sh1rk
                .toJSON()
                .prettyPrint()
                .build();

        assertEquals(jsonPretty, Sh1rkDataTest.JSON_DATA_PRETTY);
    }

    @Test
    public void createJsonTwoItem() {
        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles"),
                new Item("book").withAttribute("status", "on sale")
                        .with("author", "John Stevens")
                        .with("genre", "Winter Chronicles")
        );

        String json = sh1rk
                .toJSON()
                .build();

        assertEquals(json, Sh1rkDataTest.JSON_DATA_TWO_ITEM);

        String jsonPretty = sh1rk
                .toJSON()
                .prettyPrint()
                .build();

        assertEquals(jsonPretty, Sh1rkDataTest.JSON_DATA_PRETTY_TWO_ITEM);
    }
    @Test
    public void testWithXmlHeaderAndAddParent() {
        String etalon = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<books><book onsale=\"sale\">" +
                "<author>John</author>" +
                "</book></books>";

        Sh1rk sh1rk = Sh1rk.create(new Item("book").withAttribute("onsale", "sale").with("author", "John"));

        String result = sh1rk.toXML().withXmlHeader(Header.STANDART).addParent("books").build();
        assertEquals(result, etalon);

        result = sh1rk.toXML().withXmlHeader(1, false, "UTF-8").addParent("books").build();
        assertEquals(result, etalon);
    }
}
