package com.ej.sh1rk.transformer;

import com.ej.sh1rk.Entity;
import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkTransformerException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class Transformer declares  api  for transform module of Sh1rk framework.
 *
 */
public abstract class Transformer {
    /**
     * {@code entity} is a field of {@link Entity} which should be transformed.
     */
    protected Entity entity;
    protected boolean prettyPrint;
    protected boolean random;

    /**
     * Constructor of Transformer class.
     *
     * @param entityName object of {@link Entity} class which need to be transformed.
     */
    public Transformer(Entity entityName) {
        entity = entityName;
    }

    /**
     * Method build is public abstract for implementing
     * needed strategy in different types of Transformers
     * {@link XmlTransformer} and {@link JsonTransformer}.
     *
     * @return {@code String} representation  of {@code entity} in necessary format.
     */
    public abstract String build();

    /**
     * Method random sets the {@code random} true.
     * If this property is true, the items of {@code entity} will be build in random order.
     * By default this property id false.
     *
     * @return current {@link Transformer} instance.
     */
    public Transformer random() {
        random = true;
        return this;
    }

    /**
     * Method prettyPrint set the {@code}prettyPrint = true.
     *
     * @return current Transformer object.
     */
    public Transformer prettyPrint() {
        prettyPrint = true;
        return this;
    }

    /**
     * Method getItems returns the list of{@link Item} objects  from {@code entity}.
     * It returns list in original order if {@code random} is false.
     * And if {@code random} is true it returns the list in wihc atems sorted in random order.
     *
     * @return {@code List<Item>} items.
     */
    protected List<Item> getItems() {
        List<Item> entityItems = entity.getItems();
        List<Item> shuffled = shuffle(entityItems);
        if (random) {
            if (entityItems.size() <= 1) {
                throw new Sh1rkTransformerException("The list size for random should be more than 1");
            }
        return shuffled;
        }
        return entityItems;
    }
    private List<Item> shuffle(List<Item> list) {
        List<Item> shuffled = new ArrayList<>();
        Object[] arr = list.toArray();

        int size = list.size();
        for (int i = size; i > 1; i--) {
            swap(arr, i - 1, new Random().nextInt(i));
        }
        for (Object anArr : arr) {
            shuffled.add((Item) anArr);
        }
        return shuffled;
    }
    private  void swap(Object[] x, int a, int b) {
        Object t = x[a];
        x[a] = x[b];
        x[b] = t;
    }

    /**
     * Method isPrettyPrint.
     *
     * @return true if {@link Transformer} prettyPrint was invoked and false if wasn't.
     */
    protected boolean isPrettyPrint() {
        return prettyPrint;
    }

}
