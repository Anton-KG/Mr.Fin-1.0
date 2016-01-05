package com.ej.sh1rk;

import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Element;
import com.ej.sh1rk.data.Item;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class XmlDataTest {
    public static final String XML_DATA = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
            "<book status=\"onsale\"> " +
            "<author>John Stevens </author> " +
            "<genre> Winter Chronicles</genre> " +
            "</book>";

    public static final String XML_DATA_NOT_CLOSE_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
            "<book status=\"onsale\"> " +
            "<author>John Stevens " +
            "<genre> Winter Chronicles</genre> " +
            "</book>";

    public static final String XML_DATA_NOT_OPEN_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
            "<book status=\"onsale\"> " +
            "John Stevens </author> " +
            "<genre> Winter Chronicles</genre> " +
            "</book>";

    public static final String XML_DATA_INVALID_NAME_TAG = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
            "<book status=\"onsale\"> " +
            "<author>John Stevens </unknown> " +
            "<genre> Winter Chronicles</genre> " +
            "</book>";

    public static final String XML_DATA_PARENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> " +
            "<Books>" +
            "<book1 status=\"onsale\"> " +
            "<author>John1 Stevens1 </author> " +
            "<genre> Winter1 Chronicles1</genre> " +
            "</book1>" +
            "<book2 status=\"onsale\"> " +
            "<author>John2 Stevens2 </author> " +
            "<genre> Winter2 Chronicles2</genre> " +
            "</book2>" +
            "</Books>";

    public static void checkModel(Entity entity) {
        assertNotNull(entity);

        Item item = entity.getItems().get(0);
        List<Attribute> attributes = item.getAttributes();
        assertNotNull(attributes);
        assertEquals(attributes.size(), 1);
        assertEquals(attributes.get(0).getName(), "status");
        assertEquals(attributes.get(0).getValue(), "onsale");

        List<Element> elements = item.getElements();
        assertNotNull(elements);
        assertEquals(elements.size(), 2);
        assertEquals(elements.get(0).getName(), "author");
        assertEquals(elements.get(0).getValue(), "John Stevens");
        assertEquals(elements.get(1).getName(), "genre");
        assertEquals(elements.get(1).getValue(), "Winter Chronicles");
    }

    public static void checkParentModel(Entity entity) {
        assertNotNull(entity);

        assertEquals(entity.getParent(), "Books");

        Item item = entity.getItems().get(0);
        List<Attribute> attributes = item.getAttributes();
        assertNotNull(attributes);
        assertEquals(attributes.size(), 1);
        assertEquals(attributes.get(0).getName(), "status");
        assertEquals(attributes.get(0).getValue(), "onsale");

        List<Element> elements = item.getElements();
        assertNotNull(elements);
        assertEquals(elements.size(), 2);
        assertEquals(elements.get(0).getName(), "author");
        assertEquals(elements.get(0).getValue(), "John1 Stevens1");
        assertEquals(elements.get(1).getName(), "genre");
        assertEquals(elements.get(1).getValue(), "Winter1 Chronicles1");

        item = entity.getItems().get(1);
        attributes = item.getAttributes();
        assertNotNull(attributes);
        assertEquals(attributes.size(), 1);
        assertEquals(attributes.get(0).getName(), "status");
        assertEquals(attributes.get(0).getValue(), "onsale");

        elements = item.getElements();
        assertNotNull(elements);
        assertEquals(elements.size(), 2);
        assertEquals(elements.get(0).getName(), "author");
        assertEquals(elements.get(0).getValue(), "John2 Stevens2");
        assertEquals(elements.get(1).getName(), "genre");
        assertEquals(elements.get(1).getValue(), "Winter2 Chronicles2");
    }
}
