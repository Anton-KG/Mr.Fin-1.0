package com.ej.sh1rk.validator;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Class {@code JsonValidator} is responsible for implementation .json files validation.
 *
 */
public final class JsonValidator extends Validator {
    JsonValidator() {
        jsonParser();
        lineErrorNum();
        thePercentageOfCompletion();
        lineCounter();
        fileSize();
    }

    /**
     * This method allow to get {@code JsonValidator} object. Then uses this object
     * as parameter for creating {@link PrintOut} object. Then make output into console.
     */
    public static void getJsonValidator() {
        Validator valObj = new JsonValidator();
        PrintOut printOut = new PrintOut(valObj);
        printOut.chooseOutput();
    }

    private void jsonParser() {
        JsonParser jsonParser = new JsonParser();
        try {
            jsonParser.parse(SourceReader.getFileReader());
        } catch (JsonSyntaxException e) {
            setLineException(e.toString());
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}
