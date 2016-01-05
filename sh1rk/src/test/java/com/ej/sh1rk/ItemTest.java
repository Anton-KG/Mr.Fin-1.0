package com.ej.sh1rk;

import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Element;
import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test class for the {@code Item}.
 *
 */
public class ItemTest {

    private static final Logger LOG = LoggerFactory.getLogger(ItemTest.class);

    private static Item testItem;
    private static Item testedItem = new Item("book");

    @Before
    public void prepareTestItem() {
        testItem = ItemTestHelper.setTestItemObject();
    }

    @Test
    public void testItemName() {
        assertEquals(testedItem.getName(), testItem.getName());
    }

    @Test
    public void testAttribute() {
        Attribute attribute = new Attribute("stat", "on sale");
        assertNotEquals(attribute.hashCode(), testItem.getAttributes().get(0).hashCode());
        assertNotEquals(attribute, testItem);
        Attribute equalAttribute = testItem.getAttributes().get(0);
        assertEquals(equalAttribute, testItem.getAttributes().get(0));
    }

    @Test
    public void testWithAttribute() {
        testedItem.withAttribute("status", "on_sale");
        assertEquals(testedItem.getAttributes().get(0).getName(), testItem.getAttributes().get(0).getName());
        assertEquals(testedItem.getAttributes().get(0).getValue(), testItem.getAttributes().get(0).getValue());
    }

    @Test
    public void testWithAttributes() {
        Attribute discount = new Attribute("discount", "0");
        Attribute delivery = new Attribute("delivery", "free");
        testedItem.withAttributes(discount, delivery);
        for (int i = 1; i < testedItem.getAttributes().size(); i++) {
            assertEquals(testedItem.getAttributes().get(i).getName(), testItem.getAttributes().get(i).getName());
            assertEquals(testedItem.getAttributes().get(i).getValue(), testItem.getAttributes().get(i).getValue());
        }
    }

    @Test
    public void testElement() {
        Element element = new Element("auth", "John Stevens");
        assertNotEquals(element.hashCode(), testItem.getElements().get(0).hashCode());
        assertNotEquals(element, testItem);
        Element equalElement = testItem.getElements().get(0);
        assertEquals(equalElement, testItem.getElements().get(0));
    }

    @Test
    public void testWith() {
        testedItem.with("author", "John Stevens");
        assertEquals(testedItem.getElements().get(0).getName(), testItem.getElements().get(0).getName());
        assertEquals(testedItem.getElements().get(0).getValue(), testItem.getElements().get(0).getValue());
    }

    @Test
    public void testWithMultiple() {
        Element genre = new Element("genre", "Winter Chronicles");
        Element isbn = new Element("isbn", "978-5-17-088721-7");
        testedItem.with(genre, isbn);
        for (int i = 1; i < testedItem.getElements().size(); i++) {
            assertEquals(testedItem.getElements().get(i).getName(), testItem.getElements().get(i).getName());
            assertEquals(testedItem.getElements().get(i).getValue(), testItem.getElements().get(i).getValue());
        }
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedSeparateAttribute() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("status", "on_sale")
                .withAttribute("status", "on_sale"))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedAttributeInList() {
        String result = Sh1rk.create(new Item("book")
                .withAttribute("status", "on_sale")
                .withAttributes(new Attribute("Attribute 1", "Value 1"), new Attribute("status", "on_sale")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedAttributeWithinList() {
        String result = Sh1rk.create(new Item("book")
                .withAttributes(new Attribute("Attribute 1", "Value 1"), new Attribute("Attribute 2", "Value 2"), new
                        Attribute("Attribute 1", "Value 1")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedSeparateElement() {
        String result = Sh1rk.create(new Item("book")
                .with("Element 1", "Value 1")
                .with("Element 1", "Value 1"))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedElementInList() {
        String result = Sh1rk.create(new Item("book")
                .with("Element 1", "Value 1")
                .with(new Element("Element 2", "Value 2"), new Element("Element 1", "Value 1")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void testDuplicatedElementWithinList() {
        String result = Sh1rk.create(new Item("book")
                .with(new Element("Element 1", "Value 1"), new Element("Element 2", "Value 2"),
                        new Element("Element 1", "Value 1")))
                .toXML()
                .build();
        LOG.info("Output string -> " + result);
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void createItemTest() {
        char illegalSymbol = '&';
        String str = Sh1rk.create(new Item("secondItem" + illegalSymbol).with("author", "secondAuthor").
                withAttribute("attr2", "some attr 2")).toJSON().build();
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void ElementTest() {
        char illegalSymbol = '1';
        String str = Sh1rk.create(new Item("secondItem").with(illegalSymbol + "author", "secondAuthor").
                withAttribute("attr2", "some attr 2")).toJSON().build();
    }

    @Test(expected = Sh1rkSyntaxException.class)
    public void ElementTestT123() {
        char illegalSymbol = '1';
        String str = Sh1rk.create(new Item("secondItem").with("author", "secondAuthor").
                withAttribute(illegalSymbol + "attr2", "some attr 2")).toJSON().build();
    }
}
