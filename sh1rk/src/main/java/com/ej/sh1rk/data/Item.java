package com.ej.sh1rk.data;

import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;
import com.ej.sh1rk.CheckMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class for storing information about any item.
 *
 */
public class Item {

    private String name;
    private List<Attribute> attributes;
    private List<Element> elements;

    /**
     * Initializes a newly created {@code Item} object so that it got a name as in the argument and an empty lists of
     * attributes and elements.
     *
     * @param itemName {@code String}
     */
    public Item(String itemName) {
        if (!itemName.isEmpty()) {
            CheckMethods.checkIdentifier(itemName);
            this.name = itemName;
        } else {
            throw new Sh1rkSyntaxException("Error creating item - empty name");
        }
        attributes = new ArrayList<>();
        elements = new ArrayList<>();
    }

    /**
     * Accessor for a {@code name} field of a class.
     *
     * @return {@code String} name
     */
    public String getName() {
        return name;
    }

    /**
     * Accessor for a {@code attributes} field of a class.
     *
     * @return {@code List<Attribute>} attributes
     */
    public List<Attribute> getAttributes() {
        return Collections.unmodifiableList(attributes);
    }

    /**
     * Accessor for a {@code elements} field of a class.
     *
     * @return {@code List<Element>} elements
     */
    public List<Element> getElements() {
        return Collections.unmodifiableList(elements);
    }

    /**
     * Sets a new attribute of the {@code Item} with {@code name} and {@code value}.
     *
     * @param attributeName  {@code String} - name of the attribute
     * @param attributeValue {@code String} - value of the attribute
     * @return {@code Item} object with attribute set
     */
    public Item withAttribute(String attributeName, String attributeValue) {
        Attribute attribute = new Attribute(attributeName, attributeValue);
        checkDuplicatedAttribute(attribute);
        addAttribute(attribute);
        return this;
    }

    /**
     * Sets a list of attributes of the {@code Item} with {@code name} and {@code value}.
     *
     * @param itemAttributes {@code Attribute []} - attributes to set in {@code Item}
     * @return {@code Item} object with attribute set
     */
    public Item withAttributes(Attribute... itemAttributes) {
        for (Attribute attribute : itemAttributes) {
            checkDuplicatedAttribute(attribute);
            addAttribute(attribute);
        }
        return this;
    }

    /**
     * Sets a new element of the {@code Item} with {@code name} and {@code value}.
     *
     * @param elementName  {@code String} - name of the element
     * @param elementValue {@code String} - value of the element
     * @return {@code Item} object with element set
     */
    public Item with(String elementName, String elementValue) {
        Element element = new Element(elementName, elementValue);
        checkDuplicatedElement(element);
        addElement(element);
        return this;
    }

    /**
     * Sets a list of elements of the {@code Item} with {@code name} and {@code value}.
     *
     * @param itemElements {@code Element []} - element to set in {@code Item}
     * @return {@code Item} object with element set
     */
    public Item with(Element... itemElements) {
        for (Element element : itemElements) {
            checkDuplicatedElement(element);
            addElement(element);
        }
        return this;
    }

    private void checkDuplicatedAttribute(Attribute attribute) {
        if (attributes.contains(attribute) || attribute.getName().equals(this.getName())) {
            throw new Sh1rkSyntaxException("Error adding the attribute");
        }
    }

    private void addAttribute(Attribute attribute) {
        if (attribute.getName().isEmpty() || attribute.getValue().isEmpty()) {
            throw new Sh1rkSyntaxException("Error adding the attribute - attribute is empty");
        } else {
            attributes.add(attribute);
        }
    }

    private void checkDuplicatedElement(Element element) {
        if (elements.contains(element)) {
            throw new Sh1rkSyntaxException("Error adding the element.");
        }
    }

    private void addElement(Element element) {
        if (element.getName().isEmpty() || element.getValue().isEmpty()) {
            throw new Sh1rkSyntaxException("Error adding the element - element is empty");
        } else {
            elements.add(element);
        }
    }
}
