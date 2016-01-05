package com.ej.sh1rk;


public class Sh1rkDataTest {
    public static final String XML_DATA = "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>";

    public static final String XML_DATA_WITH_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>";

    public static final String XML_DATA_TWO_ITEM = "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>" +
            "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>";

    public static final String XML_DATA_TWO_ITEM_WITH_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
            "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>" +
            "<book status=\"on sale\">" +
            "<author>John Stevens</author>" +
            "<genre>Winter Chronicles</genre>" +
            "</book>";

    public static final String XML_DATA_PRETTY = "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>";

    public static final String XML_DATA_PRETTY_WITH_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>";

    public static final String XML_DATA_PRETTY_TWO_ITEM = "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>\n" +
            "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>";

    public static final String XML_DATA_PRETTY_TWO_ITEM_WITH_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>\n" +
            "<book status=\"on sale\">\n" +
            "    <author>John Stevens</author>\n" +
            "    <genre>Winter Chronicles</genre>\n" +
            "</book>";

    public static final String JSON_DATA = "{\"book\": {\"status\": \"on sale\"," +
            "\"author\": \"John Stevens\"," +
            "\"genre\": \"Winter Chronicles\"}}";

    public static final String JSON_DATA_TWO_ITEM = "[{\"book\": {\"status\": \"on sale\"," +
            "\"author\": \"John Stevens\"," +
            "\"genre\": \"Winter Chronicles\"}}," +
            "{\"book\": {\"status\": \"on sale\"," +
            "\"author\": \"John Stevens\"," +
            "\"genre\": \"Winter Chronicles\"}" +
            "}]";

    public static final String JSON_DATA_PRETTY = "{\n" +
            "  \"book\": {\n" +
            "    \"status\": \"on sale\",\n" +
            "    \"author\": \"John Stevens\",\n" +
            "    \"genre\": \"Winter Chronicles\"\n" +
            "  }" +
            "\n}";

    public static final String JSON_DATA_PRETTY_TWO_ITEM = "[\n" +
            "  {\n" +
            "    \"book\": {\n" +
            "      \"status\": \"on sale\",\n" +
            "      \"author\": \"John Stevens\",\n" +
            "      \"genre\": \"Winter Chronicles\"\n" +
            "    }\n" +
            "  },\n" +
            "  {\n" +
            "    \"book\": {\n" +
            "      \"status\": \"on sale\",\n" +
            "      \"author\": \"John Stevens\",\n" +
            "      \"genre\": \"Winter Chronicles\"\n" +
            "    }\n" +
            "  }\n" +
            "]";
}
