package com.ej.sh1rk;

import com.ej.sh1rk.data.Item;

import java.util.List;

/**
 * The {@code Entity} class implements object that contain header of XML document
 * and list of Item`s.
 *
 */
public class Entity {

    /*
    This field contain document`s XML header
     */
    private XmlHeader xmlHeader;

    /*
    This field contain list of Item`s
     */
    private List<Item> items;

    private String parent;

    /**
     * Default constructor for {@code Entity}.
     *
     * @param header    header of XML document
     * @param tempItems list ot Item`s
     */
    public Entity(XmlHeader header, List<Item> tempItems) {
        this.xmlHeader = header;
        this.items = tempItems;
    }

    /**
     * This method returns header of XMl document.
     *
     * @return header of XMl document
     */
    public XmlHeader getXmlHeader() {
        return xmlHeader;
    }

    /**
     * This method set document`s XML headers.
     *
     * @param header header that will be in XML document
     */
    public void setXmlHeader(XmlHeader header) {
        this.xmlHeader = header;
    }

    /**
     * This method returns list of Item`s.
     *
     * @return list of Item`s
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Return name of parent item.
     *
     * @return Name of parent item.
     */
    public String getParent() {
        return parent;
    }

    /**
     * To set name of parent item.
     *
     * @param parent Name of parent item.
     */
    public void setParent(String parent) {
        this.parent = parent;
    }
}
