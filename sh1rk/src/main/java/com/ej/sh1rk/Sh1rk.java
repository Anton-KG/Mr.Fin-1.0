package com.ej.sh1rk;

import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkIncorrectFormatException;
import com.ej.sh1rk.parser.JsonParser;
import com.ej.sh1rk.parser.Parser;
import com.ej.sh1rk.parser.XmlParser;
import com.ej.sh1rk.transformer.JsonTransformer;
import com.ej.sh1rk.transformer.XmlTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the main class for using Sh1rk.
 * An instance of {@code Sh1rk} is created by invoking {@link #create(Item... items)}
 * or {@link #scan(String data)} methods on it.
 * <p>
 * Here is an example of how Sh1rk is used:
 * <p>
 * String data = "{"book": {"status": "onsale","author": "John Stevens","genre": "Winter Chronicles"}}";
 * Sh1rk sh1rk = Sh1rk.scan(data);
 * Item book = sh1rk.getItem("book");
 * <p>
 * String xml = Sh1rk.create(new Item(“book”)
 * .withAttribute(“status”, “on sale”)
 * .with(“author”, “John Stevens”)
 * .with(“genre”, “Winter Chronicles”))
 * .toXML()
 * .build();
 */
public final class Sh1rk {
    private final Entity entity;

    /**
     * Create object of type Sh1rk.
     *
     * @param items The list an object of type {@code Item}
     *              which is initialized object {@code Sh1rk}
     * @return Object of type {@code Sh1rk}
     */
    public static Sh1rk create(Item... items) {
        return new Sh1rk(items);
    }

    /**
     * Create object of type Sh1rk.
     *
     * @param data A string containing data in XML or JSON format.
     * @return Object of type {@code Sh1rk}
     */
    public static Sh1rk scan(String data) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("String to parse 'data' cannot be null or empty");
        }

        return new Sh1rk(data);
    }

    private Sh1rk(String data) {
        Parser parser = chooseParser(data);
        entity = parser.parse(data);
    }

    private Sh1rk(Item... items) {
        List<Item> listItem = new ArrayList<>(items.length);
        for (Item item : items) {
            listItem.add(item);
        }

        entity = new Entity(null, listItem);
    }

    /**
     * Return XML transformer.
     *
     * @return {@code XmlTransformer}
     */
    public XmlTransformer toXML() {
        return new XmlTransformer(entity);
    }

    /**
     * Return JSON transformer.
     *
     * @return {@code JsonTransformer}
     */
    public JsonTransformer toJSON() {
        return new JsonTransformer(entity);
    }

    /**
     * Look for an {@code Item} by name.
     *
     * @param name The name of item that you want to find.
     * @return Returns an {@code Item} or null if not found.
     */
    public Item getItem(String name) {
        List<Item> items = entity.getItems();

        for (Item item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Return XML Header.
     *
     * @return An XML Header or null if an XML Header is not.
     */
    public XmlHeader getXmlHeader() {
        return entity.getXmlHeader();
    }

    private static Parser chooseParser(String data) {
        for (int i = 0; i < data.length(); i++) {
            char symbol = data.charAt(i);

            if (symbol == '<') {
                return new XmlParser();

            } else if (symbol == '{') {
                return new JsonParser();
            }
        }

        throw new Sh1rkIncorrectFormatException("Incorrect format of data received for scan.");
    }
}
