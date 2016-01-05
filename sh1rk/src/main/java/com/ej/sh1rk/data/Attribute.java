package com.ej.sh1rk.data;

import com.ej.sh1rk.CheckMethods;

/**
 * Class-wrapper for storing information about {@code Item} attributes.
 *
 */
public class Attribute extends Data {

    /**
     * Initializes a newly created {@code Attribute} object so that it got name and value as in the arguments.
     *
     * @param name  {@code String} - name of the attribute
     * @param value {@code String} - value of the attribute
     */
    public Attribute(String name, String value) {
        super(name, value);
        CheckMethods.checkIdentifier(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Data data = (Data) o;
        return getName().equals(data.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
