package com.ej.sh1rk.parser;

import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class used to build list of items from XML/JSON file when using the appropriate parser.
 *
 */
public class ItemsBuilder {

    /**
     * Collection the {@code Stack}.
     *
     * @param <T> Parametrized type.
     */
    private class Stack<T> {
        private LinkedList<T> storage = new LinkedList<>();

        public void push(T value) {
            storage.addFirst(value);
        }

        public T peek() {
            return storage.getFirst();
        }

        public T pop() {
            return storage.removeFirst();
        }

        public boolean isEmpty() {
            return storage.isEmpty();
        }

        public int size() {
            return storage.size();
        }
    }

    /**
     * Node.
     */
    private static class Node {
        private String name;
        private String value;
        private List<Attribute> attributes = new ArrayList<>();
        private List<Node> children = new ArrayList<>();

        private Node() {
        }

        private Node(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void addAttribute(String nameAttribute, String valueAttribute) {
            attributes.add(new Attribute(nameAttribute, valueAttribute));
        }

        public Attribute getAttribute(int index) {
            return attributes.get(index);
        }

        public int countAttributes() {
            return attributes.size();
        }

        public Node addChild(String nameChild) {
            Node child = new Node(nameChild);
            children.add(child);
            return child;
        }
    }

    private static final int LEVEL1 = 1;
    private static final int LEVEL2 = 2;
    private static final int LEVEL3 = 3;
    private static final int NESTING = 3;
    private List<Item> items = new ArrayList<>();
    private Item newItem;
    private Stack<Node> nodes = new Stack<>();
    private Node currentNode = new Node();
    private String parentName;
    private boolean hasParent;

    /**
     * Initializes a newly created {@code ItemsBuilder} object so that it got an empty list of opened tags and sets
     * the initial nesting level.
     */
    public ItemsBuilder() {
    }

    /**
     * Handles the opening tag of the item from XML/JSON file if {@code level} is {@code 0} or {@code 1}, otherwise
     * throws {@link Sh1rkSyntaxException}.
     *
     * @param tagName name of the tag
     */
    public void openTag(String tagName) {
        nodes.push(currentNode);
        currentNode = currentNode.addChild(tagName);

        if (!hasParent) {
            hasParent = nodes.size() == NESTING;
        }
    }

    /**
     * Sets an attribute for a {@code newItem} with {@code attributeName} name and {@code attributeValue} value if
     * {@code level} is {@code 0}, otherwise throws {@link Sh1rkSyntaxException}.
     *
     * @param attributeName  name of the attribute
     * @param attributeValue value of the attribute
     */
    public void setAttribute(String attributeName, String attributeValue) {
        currentNode.addAttribute(attributeName, attributeValue);
    }

    /**
     * Sets an element for a {@code newItem} with {@code openedTags[level]} name and {@code elementValue} value if
     * {@code level} is {@code 0}, otherwise throws {@link Sh1rkSyntaxException}.
     *
     * @param elementValue value of the element
     */
    public void setElementValue(String elementValue) {
        currentNode.setValue(elementValue);
    }

    /**
     * Handles the closing tag of the item from XML/JSON file if {@code level} is {@code 0} or {@code 1}, otherwise
     * throws {@link Sh1rkSyntaxException}.
     * Adding the {@code newItem} element to the {@code items} list if {@code level} is {@code 0}.
     *
     * @param tagName name of the tag
     */
    public void closeTag(String tagName) {
        if (nodes.isEmpty()) {
            throw new Sh1rkSyntaxException("Unable to close the tag - tag is not opened");
        }

        if (!currentNode.getName().equals(tagName)) {
            throw new Sh1rkSyntaxException("Unable to close the tag - wrong tag name");
        }

        if (hasParent) {
            switch (nodes.size()) {
                case LEVEL1:
                    parentName = currentNode.getName();
                    break;

                case LEVEL2:
                    items.add(newItem);
                    newItem = null;
                    break;

                case LEVEL3:
                    createItem();
                    break;

                default:
                    throw new Sh1rkSyntaxException("Excess levels of nesting");
            }
        } else {
            switch (nodes.size()) {
                case LEVEL1:
                    items.add(newItem);
                    newItem = null;
                    break;

                case LEVEL2:
                    createItem();
                    break;

                default:
                    throw new Sh1rkSyntaxException("Excess levels of nesting");
            }
        }

        currentNode = nodes.pop();
    }

    private void createItem() {
        Node node = nodes.peek();
        if (newItem == null) {
            newItem = new Item(node.getName());

            for (int i = 0; i < node.countAttributes(); ++i) {
                newItem.withAttributes(node.getAttribute(i));
            }
        }

        newItem.with(currentNode.getName(), currentNode.getValue());
    }

    /**
     * Accessor for an {@code items} field of the class.
     *
     * @return list of {@code Item}
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Get parent to item(s).
     *
     * @return Name of parent item(s).
     */
    public String getParent() {
        return parentName;
    }
}
