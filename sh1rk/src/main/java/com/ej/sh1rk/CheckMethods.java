package com.ej.sh1rk;

import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;

import java.util.regex.Pattern;

/**
 * The {@code CheckMethods} class implements methods for checking different input values.
 *
 */
public class CheckMethods {

    private static Pattern identifierXML = Pattern.compile("[_a-zA-Z]+[_.:a-zA-Z0-9]*");

    /**
     * This method responsible for checking string.
     *
     * @param value string that would be checked
     */
    public static void checkIdentifier(String value) {
        if (!identifierXML.matcher(value).matches()) {
            throw new Sh1rkSyntaxException("Illegal argument");
        }
    }
}

