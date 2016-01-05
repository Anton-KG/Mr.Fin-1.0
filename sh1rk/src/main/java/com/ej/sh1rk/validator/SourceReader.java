package com.ej.sh1rk.validator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Class {@code SourceReader} is responsible for right reading of the source file.
 *
 */
public class SourceReader {

    private static String path;

    /**
     * Method returns file source for {@link Validator}, {@link XmlValidator}.
     *
     * @return file source
     */
    public static File getFile() {
        File reader = null;
        SourceReader sr = null;
        sr = new SourceReader();
        reader = sr.fileSource();
        return reader;
    }

    /**
     * Method returns fileReader source for {@link Validator}, {@link JsonValidator},
     * {@link ChooseParser}.
     *
     * @return fileReader source
     */
    public static FileReader getFileReader() {
        FileReader reader = null;
        try {
            reader = new FileReader(getFile());
        } catch (FileNotFoundException e) {
            System.out.println("Please make sure that the file name is correct and it is present in this directory");
        }
        return reader;
    }

    /**
     * Method returns String data for StringTokenizer in class {@link XmlValidator}.
     *
     * @return String data for {@code hasHeader()}
     */
    public static String readFileToString() {
        byte[] encoded = new byte[0];
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Please make sure that the file name is correct and it is present in this directory");
        }
        return new String(encoded);
    }

    /**
     *This method set path to file.
     *
     * @param pathToFile String from console input
     */
    public void setPath(String pathToFile) {
        path = pathToFile;
    }

    private File fileSource() {
        File file = null;
        file = new File(path);
        return file;
    }
}
