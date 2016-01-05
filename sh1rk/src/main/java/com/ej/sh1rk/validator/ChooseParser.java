package com.ej.sh1rk.validator;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Class {@code ChooseParser} is responsible for choose correct parser for the document.
 *
 */
public final class ChooseParser {

    private static final byte CHAR_BUF = 127;
    private static final char SYMBOL_XML = '<';
    private static final char SYMBOL_JSON1 = '[';
    private static final char SYMBOL_JSON2 = '{';

    private ChooseParser() {
    }

    /**
     * This method responsible for start file validation.
     *
     * @param action that can be apples to the file
     * @param source path to file
     */
    public static void validate(String action, String source) {
        SourceReader sr = new SourceReader();
        sr.setPath(source);
        if (action.equals("validate")) {
            choose();
        } else {
            System.out.println("Usage: java -jar Sh1rk.jar validate some.xml"
                    + " or some.json");
        }
    }

    private static void choose() {
        char result;
        char[] buf = new char[CHAR_BUF];
        BufferedReader br = new BufferedReader(SourceReader.getFileReader());
        try {
            br.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (char aBuf : buf) {
            result = aBuf;
            if (result == SYMBOL_XML) {
                XmlValidator.getXmlValidator();
                break;
            }
            if (result == SYMBOL_JSON1 || result == SYMBOL_JSON2) {
                JsonValidator.getJsonValidator();
                break;
            }
        }
    }
}

