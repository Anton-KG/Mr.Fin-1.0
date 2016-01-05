package com.ej.sh1rk.validator;

/**
 * Class {@code PrintOut} is responsible for printing status of verified files.
 *
 */
public class PrintOut {
    private static final boolean POSITIVE_STATUS = true;
    private static final String FILE_TYPE = "File type:...";
    private static final String SH1RK_VALIDATION_OK = "Sh1rk Validation...OK";
    private static final String SH1RK_VALIDATION_FAILURE = "Sh1rk Validation...FAILURE";
    private static final String SIZE_2F = "Size...%.2f";
    private static final String XML_HEADER = "XML Header...";
    private static final String PERCENTAGE_OF_VALIDATION_DATA_2F_N = "Percentage of validation data...%.2f%n";
    private static final String THAT_STOP_THE_VALIDATION = "that stop the validation...";

    private Validator valObj;

    PrintOut(Validator valObject) {
        this.valObj = valObject;
    }

    /**
     * Method {@code chooseOutput()} choose one of the parser output.
     */
    public  void chooseOutput() {
        if (valObj instanceof XmlValidator) {
            if (valObj.getStatus() == POSITIVE_STATUS) {
                successOutput();
            } else {
                failureOutput();
            }
        }
        if (valObj instanceof JsonValidator) {
            if (valObj.getStatus() == POSITIVE_STATUS) {
                successOutput();
            } else {
                failureOutput();
            }
        }
    }

    private void successOutput() {
        if (valObj instanceof XmlValidator) {
            System.out.println("\n" + FILE_TYPE + "XML"
                    + "\n" + SH1RK_VALIDATION_OK
                    + "\n" + XML_HEADER + ((XmlValidator) valObj).getHasHeader()
                    + "\nLines of XML data..." + valObj.getLineCounter());
            System.out.printf(SIZE_2F, valObj.getFileSize());
            System.out.print(valObj.getDimensions());
            System.out.println();
        }
        if (valObj instanceof JsonValidator) {
            System.out.println("\n" + FILE_TYPE + "JSON"
                    + "\n" + SH1RK_VALIDATION_OK
                    + "\nLines of JSON data..." + valObj.getLineCounter());
            System.out.printf(SIZE_2F, valObj.getFileSize());
            System.out.print(valObj.getDimensions());
            System.out.println();
        }
    }

    private void failureOutput() {
        if (valObj instanceof XmlValidator) {
            System.out.println("\n" + FILE_TYPE + "XML"
                    + "\n" + SH1RK_VALIDATION_FAILURE
                    + "\n" + XML_HEADER + ((XmlValidator) valObj).getHasHeader());
            System.out.format(PERCENTAGE_OF_VALIDATION_DATA_2F_N,
                    valObj.getPercentage());
            System.out.println("Line of xml " + THAT_STOP_THE_VALIDATION
                    + valObj.getLineErrorNum() + " line");
        }
        if (valObj instanceof JsonValidator) {
            System.out.println("\n" + FILE_TYPE + "JSON"
                    + "\n" + SH1RK_VALIDATION_FAILURE);
            System.out.format(PERCENTAGE_OF_VALIDATION_DATA_2F_N,
                    valObj.getPercentage());
            System.out.println("Line of json " + THAT_STOP_THE_VALIDATION
                    + valObj.getLineErrorNum() + " line");
        }
    }
}
