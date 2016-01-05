package com.ej.sh1rk;

/**
 * The {@code XmlHeader} class implements object that represents header for XML document.
 */
public class XmlHeader {

    private final double xmlVersion;
    private final boolean standAlone;
    private final String encoding;

    /**
     * Default constructor for {@code XmlHeader}.
     *
     * @param version    version of XML document
     * @param standAlone header`s property about existence marking description
     * @param encoding   file encoding type
     */
    public XmlHeader(double version, boolean standAlone, String encoding) {
        if (version < 1) {
            throw new IllegalArgumentException("The version argument is invalid. It must not be less than 1.");
        }

        if (encoding == null || encoding.isEmpty() || encoding.trim().length() == 0) {
            throw new IllegalArgumentException("The encoding argument is invalid. It must not be null or empty string" +
                    ".");
        }

        this.xmlVersion = version;
        this.standAlone = standAlone;
        this.encoding = encoding;
    }

    /**
     * Get version of the document.
     *
     * @return version of the document
     */
    public double getXmlVersion() {
        return xmlVersion;
    }

    /**
     * Get property about existence header`s marking description.
     *
     * @return true if to the document will be connect marking description and false if not
     */
    public boolean isStandAlone() {
        return standAlone;
    }

    /**
     * Get header`s encoding type.
     *
     * @return header`s encoding type
     */
    public String getEncoding() {
        return encoding;
    }
}
