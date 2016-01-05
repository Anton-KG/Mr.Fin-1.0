package com.ej.sh1rk;

import com.ej.sh1rk.exceptions.Sh1rkParserException;
import com.ej.sh1rk.parser.JsonParser;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static org.junit.Assert.assertFalse;

/**
 * Class JsonParserTest implements the tests for {@link JsonParser} class.
 */
public class JsonParserTest {
    private JsonParser jsonParser;

    @Before
    public void setUp() {
        jsonParser = new JsonParser();
    }

    @Test
    public void testParse() throws Exception {
        assertFalse("The list of items after parsing shouldn't be null or empty",
                jsonParser.parse(loadFile("/jsontestObjectArray.json")).getItems().isEmpty());
        assertFalse("The list of items after parsing shouldn't be null or empty",
                jsonParser.parse(loadFile("/jsontestObject.json")).getItems().isEmpty());
    }

    @Test(expected = Sh1rkParserException.class)
    public void testSh1rkParseExceptionParse() {
        String empty = "";
        jsonParser.parse(empty);
    }

    private String loadFile(String pathFile) throws Exception {
        File file = new File(this.getClass().getResource(pathFile).toURI());
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String str;
            while ((str = in.readLine()) != null) {
                if (sb.length() != 0) {
                    sb.append("\n");
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }
}