package com.ej.sh1rk;

import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Element;
import com.ej.sh1rk.data.Item;

/**
 * Class for setting test {@code Item}.
 *
 */
public class ItemTestHelper {

    public static Item setTestItemObject() {
        return new Item("book")
                .withAttributes(new Attribute("status", "on_sale"),
                        new Attribute("discount", "0"),
                        new Attribute("delivery", "free"))
                .with(new Element("author", "John Stevens"),
                        new Element("genre", "Winter Chronicles"),
                        new Element("isbn", "978-5-17-088721-7"));
    }

}