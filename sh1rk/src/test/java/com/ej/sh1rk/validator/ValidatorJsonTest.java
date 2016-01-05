package com.ej.sh1rk.validator;

import com.ej.sh1rk.validator.SourceReader;
import com.ej.sh1rk.validator.Validator;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the {@code Validator} class, namely it`s JSON part.
 *
 */
public class ValidatorJsonTest {
    private static String[] args;

    private String getFilePath(String fileName) {
        return new File("").getAbsolutePath() + File.separator
                + "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + fileName;
    }

    @Test
    public void validateJsonOkTest() {
        String fileName = "work.json";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateJsonFailureTest() {
        String fileName = "failure.json";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateSmallFileTest() {
        String fileName = "ispretty.json";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateFewArgTest() {
        args = new String[]{"validate"};
        Validator.main(args);
    }

    @Test
    public void validateEmptyString() {
        String fileName = "empty_string.json";
        String filepath = getFilePath(fileName);

        SourceReader sourceReader = new SourceReader();
        sourceReader.setPath(filepath);
        Validator validator = new Validator();

        assertEquals(validator.lineCounter(), 31);
    }
}
