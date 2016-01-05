package com.ej.sh1rk.parser;

import com.ej.sh1rk.Entity;

/**
 * A {@code Parser} interface provides that classes have to make implementation for the inputted file.
 * Input file has type String and contain inside string representation of {@code ".json"} or
 * {@code ".xml"} file structure.
 * <p>
 *
 */
public interface Parser {

    /**
     * Method that implement this have to parse string that can contain different file`s structure inside.
     *
     * @param data input string that will be parse
     * @return Model object
     */
    Entity parse(String data);
}
