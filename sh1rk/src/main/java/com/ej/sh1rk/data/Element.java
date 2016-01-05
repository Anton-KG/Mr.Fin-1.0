package com.ej.sh1rk.data;

import com.ej.sh1rk.CheckMethods;

/**
 * Class-wrapper for storing information about {@code Item} elements.
 *
 */
public class Element extends Data {

    /**
     * Initializes a newly created {@code Element} object so that it got name and value as in the arguments.
     *
     * @param name  {@code String} - name of the element
     * @param value {@code String} - value of the element
     */
    public Element(String name, String value) {
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
        return getName().equals(data.getName()) && getValue().equals(data.getValue());
    }

    @Override
    public int hashCode() {
        final int i = 31;
        int result = getName().hashCode();
        result = i * result + getValue().hashCode();
        return result;
    }

}
