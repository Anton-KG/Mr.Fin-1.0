package com.ej.sh1rk;

import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Element;
import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class for testing of {@code Item} empty names, {@code Attribute} and {@code Element} empty names and values.
 *
 */
public class ItemEmptyValuesTest {

    private static final Logger LOG = LoggerFactory.getLogger(ItemEmptyValuesTest.class);

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptyItemName() {
        String result = Sh1rk.create(new Item("")
                .withAttribute("status", "on_sale"))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateAttributeName() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("", "on_sale"))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateAttributeValue() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("status", ""))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptyAttributeNameInList() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("status", "on_sale")
                .withAttributes(new Attribute("", "Value 1"), new Attribute("Attribute 2", "Value 2")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptyAttributeValueInList() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("status", "on_sale")
                .withAttributes(new Attribute("Attribute 1", "Value 1"), new Attribute("Attribute 2", "")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateElementName() {
        String result = Sh1rk.create(new Item("book")
                .with("", "John Smith"))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateElementValue() {
        String result = Sh1rk.create(new Item("book")
                .with("author", ""))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateElementNameInList() {
        String result = Sh1rk.create(new Item("book")
                .with(new Element("", "John Smith"), new Element("author", "Sam Jackson")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testEmptySeparateValueInList() {
        String result = Sh1rk.create(new Item("book")
                .with(new Element("author", "John Smith"), new Element("author", "")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

}
