package com.ej.sh1rk.validator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Class {@code Validator} is validate XML or Json files for a simple structure and syntax errors.
 *
 */

public class Validator {

    private static final double MB = 1048576D;
    private static final double KB = 1024D;
    private static final double ABSOLUTE_PERCENTAGE = 100D;
    private String lineExceptions;
    private String dimension;
    private double percentage;
    private double fileSize;
    private int lineErrorNum;
    private int lineCounter;

    /**
     * The method responsible for the file`s validation running.
     *
     * @param args Array arguments from commandline
     */
    public static void main(String[] args) {
        try {
            ChooseParser.validate(args[0], args[1]);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Usage: java -jar Sh1rk.jar validate some.xml"
                    + " or some.json");
        }
    }

    int lineCounter() {
        try {
            try (BufferedReader bufferedReader = new BufferedReader(SourceReader.getFileReader())) {
                lineCounter = 0;

                String currentLine = bufferedReader.readLine();
                while (currentLine != null) {
                    if (!currentLine.isEmpty() && currentLine.trim().length() != 0) {
                        ++lineCounter;
                    }

                    currentLine = bufferedReader.readLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineCounter;
    }

    double fileSize() {
        File file = SourceReader.getFile();
        if (file.exists()) {
            if (file.length() < KB) {
                fileSize = file.length();
                dimension = "b";
            }
            if (file.length() > KB & file.length() < MB) {
                fileSize = file.length() / KB;
                dimension = "Kb";
            }
            if (file.length() > MB) {
                fileSize = file.length() / MB;
                dimension = "Mb";
            }
        }
        return fileSize;
    }

    int lineErrorNum() {
        int num = 0;
        if (lineExceptions != null) {
            String temp;
            ArrayList<String> words = new ArrayList<>();
            String exceptionLine = lineExceptions;
            StringTokenizer stringTokenizer = new StringTokenizer(exceptionLine);
            while (stringTokenizer.hasMoreTokens()) {
                temp = stringTokenizer.nextToken();
                words.add(temp);
            }
            for (int i = 0; i < words.size(); i++) {
                temp = words.get(i);
                if (temp.equalsIgnoreCase("lineNumber:")) {
                    String temp2 = words.get(i + 1).substring(0, words.get(i + 1).length() - 1);
                    num = Integer.parseInt(temp2);
                }
                if (temp.equalsIgnoreCase("line")) {
                    num = Integer.parseInt(words.get(i + 1));
                }
            }
        }
        lineErrorNum = num;
        return lineErrorNum;
    }

    double thePercentageOfCompletion() {
        double numLineEx = lineErrorNum();
        double result = ABSOLUTE_PERCENTAGE;
        if (numLineEx != 0) {
            result = (numLineEx * ABSOLUTE_PERCENTAGE) / (double) lineCounter();
        }
        percentage = result;
        return percentage;
    }

    /**
     * This method responsible for return state of document.
     *
     * @return true if document is valid or false if it isn`t
     */
    public boolean getStatus() {
        return percentage == ABSOLUTE_PERCENTAGE;
    }

    /**
     * This method returns line number where mistake occurs.
     *
     * @return number of line where mistake occurs
     */
    public int getLineErrorNum() {
        return lineErrorNum;
    }

    /**
     * This method returns valid part percentage representation of the not valid document.
     *
     * @return valid part percentage representation of the not valid document
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * This method returns file size.
     *
     * @return file size
     */
    public double getFileSize() {
        return fileSize;
    }

    /**
     * This line number where mistake occurs.
     *
     * @return number of line where mistake occurs
     */
    public int getLineCounter() {
        return lineCounter;
    }

    /**
     * This method return information about dimensions of file size.
     *
     * @return information abut dimensions
     */
    public String getDimensions() {
        return dimension;
    }

    protected void setLineException(String lineException) {
        lineExceptions = lineException;
    }
}
