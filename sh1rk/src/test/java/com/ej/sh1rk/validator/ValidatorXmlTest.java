package com.ej.sh1rk.validator;

import com.ej.sh1rk.validator.SourceReader;
import com.ej.sh1rk.validator.Validator;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the {@code Validator} class, namely it`s XML part.
 *
 */
public class ValidatorXmlTest {
    private static String[] args;

    private String getFilePath(String fileName) {
        return new File("").getAbsolutePath() + File.separator
                + "src" + File.separator + "test" + File.separator +
                "resources" + File.separator + fileName;
    }

    @Test
    public void validateTest() {
        String fileName = "work.xml";
        String filepath = getFilePath(fileName);
        args = new String[]{"NoParam", filepath};
        String result = "Usage: java -jar Sh1rk.jar validate some.xml or some.json";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        Validator.main(args);
        System.out.flush();
        System.setOut(old);
        Assert.assertEquals(result, baos.toString().trim());
    }

    @Test
    public void validateXmlOkTest() {
        String fileName = "work.xml";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateXmlFailureTest() {
        String fileName = "failure.xml";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateXmlFailureWoHeadTest() {
        String fileName = "failure_woh.xml";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateXmlOkWoHeadTest() {
        String fileName = "work_woh.xml";
        String filepath = getFilePath(fileName);
        args = new String[]{"validate", filepath};
        Validator.main(args);
    }

    @Test
    public void validateEmptyString() {
        String fileName = "empty_string.xml";
        String filepath = getFilePath(fileName);

        SourceReader sourceReader = new SourceReader();
        sourceReader.setPath(filepath);
        Validator validator = new Validator();

        assertEquals(validator.lineCounter(), 38);
    }
}
