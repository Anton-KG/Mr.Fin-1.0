package com.ej.sh1rk.data;

/**
 * Class for storing information about any attribute or element of the item.
 *
 */
public abstract class Data {

    private String name;
    private String value;

    /**
     * Initializes a newly created {@code Data} object so that it got name and value as in the arguments.
     *
     * @param dataName  {@code String} - name of the data object
     * @param dataValue {@code String} - value of the data object
     */
    public Data(String dataName, String dataValue) {
        name = dataName;
        value = dataValue;
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
     * Accessor for a {@code value} field of a class.
     *
     * @return {@code String} value
     */
    public String getValue() {
        return value;
    }

}
