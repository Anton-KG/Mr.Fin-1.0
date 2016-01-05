package com.ej.sh1rk.transformer;

import com.ej.sh1rk.Entity;
import com.ej.sh1rk.data.Data;
import com.ej.sh1rk.data.Item;

import java.util.List;

/**
 * The {@code JsonTransformer} class is converts list of objects
 * type {@code Item} to a json format string.
 */
public class JsonTransformer extends Transformer {
    private static final String START_ARRAY = "[";
    private static final String END_ARRAY = "]";
    private static final String START_OBJECT = "{";
    private static final String END_OBJECT = "}";
    private static final String INDENT = "  ";

    private final StringBuilder sb = new StringBuilder();

    /**
     * Initializes a newly created {@code JsonTransformer} object.
     *
     * @param entity An object of type {@code Entity}
     */
    public JsonTransformer(Entity entity) {
        super(entity);
    }


    /**
     * Method build transforms the {@code entity} in json format.
     *
     * @return String as representation of current entity
     */
    @Override
    public String build() {
        sb.setLength(0);

        List<Item> items = getItems();

        if (items.size() == 1) {
            outputItem(items.get(0), 0);
        } else {
            outputItems(items);
        }

        return sb.toString();
    }

    private void outputItems(List<Item> items) {
        sb.append(START_ARRAY);
        if (isPrettyPrint()) {
            sb.append("\n");
        }

        for (int i = 0; i < items.size(); ++i) {
            if (i != 0) {
                sb.append(",");
                if (isPrettyPrint()) {
                    sb.append("\n");
                }
            }

            outputItem(items.get(i), 1);
        }

        if (isPrettyPrint()) {
            sb.append("\n");
        }
        sb.append(END_ARRAY);
    }

    private void outputItem(Item item, int levelIndent) {
        outputIndent(levelIndent);
        sb.append(START_OBJECT);

        outputBeginItem(item, levelIndent + 1);
        outputItemData(item.getAttributes(), levelIndent + 2);
        if (!item.getAttributes().isEmpty() && !item.getElements().isEmpty()) {
            sb.append(",");
        }
        outputItemData(item.getElements(), levelIndent + 2);
        outputEndItem(levelIndent + 1);

        outputIndent(levelIndent);
        sb.append(END_OBJECT);
    }

    private void outputBeginItem(Item item, int levelIndent) {
        outputNewLine();
        outputIndent(levelIndent);

        sb.append("\"");
        sb.append(item.getName());
        sb.append("\": ");
        sb.append(START_OBJECT);
    }

    private void outputEndItem(int levelIndent) {
        outputNewLine();
        outputIndent(levelIndent);

        sb.append(END_OBJECT);
        outputNewLine();
    }

    private <T extends Data> void outputItemData(List<T> itemData, int levelIndent) {
        for (int i = 0; i < itemData.size(); i++) {
            if (i != 0) {
                sb.append(",");
            }

            outputNewLine();
            outputIndent(levelIndent);

            Data data = itemData.get(i);
            sb.append("\"");
            sb.append(data.getName());
            sb.append("\": \"");
            sb.append(data.getValue());
            sb.append("\"");
        }
    }

    private void outputIndent(int level) {
        if (isPrettyPrint()) {
            for (int i = 0; i < level; ++i) {
                sb.append(INDENT);
            }
        }
    }

    private void outputNewLine() {
        if (isPrettyPrint()) {
            sb.append("\n");
        }
    }
}
