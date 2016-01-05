package com.ej.sh1rk.transformer;

import com.ej.sh1rk.data.Item;

/**
 * Formatter class is abstract class.
 * It design  to provide some api for {@link XmlTransformer}  class.
 * For formatting the item {@link Item}.
 *
 */
public abstract class Formatter {
    /**
     * Xml open bracket.
     */
    public static final char OPEN_BRACKET = '<';
    /**
     * Xml close bracket.
     */
    public static final char CLOSE_BRACKET = '>';
    /**
     * Xml end bracket.
     */
    public static final String END_BRACKET = "</";
    /**
     * String tree spaces.
     */
    public static final String FOUR_SPACE_INDENT = "    ";
    /**
     * String new line.
     */
    public static final String NEXTLINE = "\n";

    /**
     * Method format procced the item to return it as needed String data.
     *
     * @param item      -object of class{@link Item} which need to be formatted.
     * @param hasParent Has a parent.
     * @return {@code String} as formatted {@code item}.
     */
    public abstract String format(Item item, boolean hasParent);
}
