package com.ej.sh1rk.transformer;

/**
 * This enum responsible for creation and and storing headers.
 */
public enum Header {
    /**
     * Enum constant {@code STANDART} which is store data for some frequently required  header.
     */
    STANDART(1.0, false, "UTF-8");
    private double version;
    private boolean standalone;
    private String encoding;

    /**
     * Constructor for {@code Header}.
     *
     * @param versionNumber   version of XML document
     * @param standaloneValue header1s property about existence marking description
     * @param encodingValue   file encoding type
     */
    Header(double versionNumber, boolean standaloneValue, String encodingValue) {
        this.version = versionNumber;
        this.standalone = standaloneValue;
        this.encoding = encodingValue;
    }

    /**
     * Accessor for @param version.
     *
     * @return version of {@link com.ej.sh1rk.XmlHeader}
     */
    public double getVersion() {
        return version;
    }

    /**
     * Method isStandalone verifies the property {@code standalone}.
     * @return standalone state of header
     */
    public boolean isStandalone() {
        return standalone;
    }

    /**
     * Accessor for {@code encoding}.
     *
     * @return {@code String} encoding.
     */
    public String getEncoding() {
        return encoding;
    }
}

