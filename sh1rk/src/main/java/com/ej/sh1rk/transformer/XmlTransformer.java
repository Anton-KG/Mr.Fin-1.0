package com.ej.sh1rk.transformer;

import com.ej.sh1rk.CheckMethods;
import com.ej.sh1rk.Entity;
import com.ej.sh1rk.XmlHeader;
import com.ej.sh1rk.data.Attribute;
import com.ej.sh1rk.data.Element;
import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkTransformerException;

import java.util.List;

/**
 * XmlTransformer class is responsible  for
 * transformation the processing {@code entity}{@link Entity} class in xml style String.
 *
 */
public class XmlTransformer extends Transformer {
    /**
     * Constructor for XmlTransformer class.
     *
     * @param entity -object {@link Entity} which need to be build.
     */
    public XmlTransformer(Entity entity) {
        super(entity);
    }

    /**
     * Method build  implements xml transform strategy for building {@code entity}.
     *
     * @return {@code entityString} as String representation of entity.
     * @throws Sh1rkTransformerException if the list of items to build is empty.
     */
    @Override
    public String build() {
        List<Item> items = getItems();
        if (items.size() == 0) {
            throw new Sh1rkTransformerException("The list of items is empty");
        }

        StringBuilder entityString = new StringBuilder();

        if (entity.getXmlHeader() != null) {
            entityString.append(formatHeader(entity.getXmlHeader()));
        }
        Formatter xmlFormatter = new XmlFormatter();

        boolean addParent = hasParent();

        if (addParent) {
            entityString.append(Formatter.OPEN_BRACKET + entity.getParent() + Formatter.CLOSE_BRACKET);

            if (prettyPrint) {
                entityString.append("\n");
            }
        }

        for (int i = 0; i < items.size(); ++i) {
            entityString.append(xmlFormatter.format(items.get(i), addParent));

            if (prettyPrint && items.size() > 1 && i < items.size() - 1) {
                entityString.append("\n");
            }
        }

        if (addParent) {
            if (prettyPrint) {
                entityString.append("\n");
            }

            entityString.append(Formatter.END_BRACKET + entity.getParent() + Formatter.CLOSE_BRACKET);
        }

        return entityString.toString();
    }
    /**
     * Class XmlFormatter is an inner class.
     * It responsible for formatting one current {@link Item} {@code} item
     * in xml style format
     */
    private class XmlFormatter extends Formatter {

        private StringBuilder itemString;

        /*
        * Method format does formatting of item
        *
        * @param item {@code Item} - object item that need to de formatted
        * @return {@code String} - itemString as String representation of item
        * Also method format checks if prettyPrint field is true, the {@itemString}
        * will be a structured xml representation of item.
        * */
        @Override
        public String format(Item item, boolean hasParent) {
            itemString = new StringBuilder();
            if (isPrettyPrint()) {
                return buildPrettyItem(item, hasParent);
            }
            return buildItem(item, hasParent);
        }

        private String buildItem(Item item, boolean hasParent) {
            int elementsCount = item.getElements().size();
            buildItemName(item, hasParent);
            buildElements(item, elementsCount);
            itemString.append(END_BRACKET);
            itemString.append(item.getName());
            itemString.append(CLOSE_BRACKET);
            return itemString.toString();
        }

        private String buildPrettyItem(Item item, boolean hasParent) {
            int elementsCount = item.getElements().size();
            buildItemName(item, hasParent);

            for (int i = 0; i < elementsCount; i++) {
                Element current = item.getElements().get(i);
                itemString.append(NEXTLINE);
                if (hasParent) {
                    itemString.append(FOUR_SPACE_INDENT);
                }
                itemString.append(FOUR_SPACE_INDENT);
                itemString.append(OPEN_BRACKET);
                itemString.append(current.getName());
                itemString.append(CLOSE_BRACKET);
                itemString.append(current.getValue());
                itemString.append(END_BRACKET);
                itemString.append(current.getName());
                itemString.append(CLOSE_BRACKET);
                if (i == elementsCount - 1) {
                    itemString.append(NEXTLINE);
                }
            }

            if (hasParent) {
                itemString.append(FOUR_SPACE_INDENT);
            }
            itemString.append(END_BRACKET);
            itemString.append(item.getName());
            itemString.append(CLOSE_BRACKET);

            return itemString.toString();
        }

        private void buildElements(Item item, int elementsCount) {
            for (int i = 0; i < elementsCount; i++) {
                Element current = item.getElements().get(i);
                itemString.append(OPEN_BRACKET);
                itemString.append(current.getName());
                itemString.append(CLOSE_BRACKET);
                itemString.append(current.getValue());
                itemString.append(END_BRACKET);
                itemString.append(current.getName());
                itemString.append(CLOSE_BRACKET);
            }
        }

        private void buildItemName(Item item, boolean hasParent) {
            if (isPrettyPrint() && hasParent) {
                itemString.append(FOUR_SPACE_INDENT);
            }

            int attributesCount = item.getAttributes().size();
            itemString.append(OPEN_BRACKET);
            itemString.append(item.getName());
            for (int i = 0; i < attributesCount; i++) {
                Attribute current = item.getAttributes().get(i);
                itemString.append(" " + current.getName() + "=" + "\"" + current.getValue() + "\"");
            }
            itemString.append(CLOSE_BRACKET);
        }
    }

    private String formatHeader(XmlHeader xmlHeader) {

        StringBuilder headerString = new StringBuilder();
        headerString.append(Formatter.OPEN_BRACKET);
        headerString.append("?xml version=");
        headerString.append(new String("\"" + xmlHeader.getXmlVersion() + "\""));
        if (xmlHeader.isStandAlone()) {
            headerString.append(" standalone=" + "\"" + "true" + "\"");
        }
        headerString.append(" encoding=");
        headerString.append("\"");
        headerString.append(xmlHeader.getEncoding());
        headerString.append("\"" + "?");
        headerString.append(Formatter.CLOSE_BRACKET);
        if (isPrettyPrint()) {
            headerString.append(Formatter.NEXTLINE);
        }
        return headerString.toString();
    }

    /**
     * Method withXmlHeader set {@code xmlHeader}to {@code entity}.
     * For building current {@code entity} with header.
     *
     * @param header is referenсe enum {@link Header}.
     * @return current {@link XmlTransformer} instance.
     */
    public XmlTransformer withXmlHeader(Header header) {
        if (header != null) {
            return withXmlHeader(header.getVersion(), header.isStandalone(), header.getEncoding());
        } else {
            throw new Sh1rkTransformerException("The Header couldn't be empty");
        }
    }

    /**
     * Method prettyPrint sets the {@code prettyPrint} true.
     * If this property is true, the items of {@code entity} will be build in pretty format.
     * By default this property id false.
     *
     * @return current {@link XmlTransformer} instance.
     */
    @Override
    public XmlTransformer prettyPrint() {
        prettyPrint = true;
        return this;
    }

    /**
     * Method random sets the {@code random} true.
     * If this property is true, the items of {@code entity} will be build in random order.
     * By default this property id false.
     *
     * @return current {@link XmlTransformer} instance.
     */
    @Override
    public XmlTransformer random() {
        random = true;
        return this;
    }
    /**
     * Method withXmlHeader set {@code xmlHeader} to {@code entity}.
     *
     * @param versionNumber   {@code double} number of xmlHeaderVersion
     * @param standaloneValue {@code boolean} value to recognize if header is standalone.
     * @param encodingValue   {@code String} encoding value such as "UTF-8" etc.
     * @return current {@link XmlTransformer} instance.
     */
    public XmlTransformer withXmlHeader(double versionNumber, boolean standaloneValue, String encodingValue) {
        entity.setXmlHeader(new XmlHeader(versionNumber, standaloneValue, encodingValue));
        return this;
    }
    /**
     * Set parent to item(s).
     *
     * @param parent Name of parent item(s).
     * @return current {@link XmlTransformer} instance.
     */
    public XmlTransformer addParent(String parent) {
        CheckMethods.checkIdentifier(parent);
        entity.setParent(parent);
        return this;
    }
    private boolean hasParent() {
        String parent = entity.getParent();
        return parent != null && !parent.isEmpty();
    }
}
