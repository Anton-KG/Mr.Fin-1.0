package com.ej.sh1rk.parser;

import com.ej.sh1rk.Entity;
import com.ej.sh1rk.exceptions.Sh1rkParserException;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class JsonParser is responsible for parsing the String {@code data} from json format.
 */
public class JsonParser implements Parser {

    private ItemsBuilder itemsBuilder;

    /**
     * Method parse -parses the input data with json  tokens parsing methods.
     *
     * @param data input string which is need to be parsed.
     * @return {@code entity} {@link Entity}.
     */
    @Override
    public Entity parse(String data) {
        if (data == null || data.isEmpty()) {
            throw new Sh1rkParserException("Input data to parse couldn't be empty or null");
        }
        itemsBuilder = new ItemsBuilder();
        JsonReader jsonReader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(data.getBytes())));
        try {
            if (jsonReader.peek() == JsonToken.BEGIN_OBJECT) {
                handleObject(jsonReader);
            }
            if (jsonReader.peek() == JsonToken.BEGIN_ARRAY) {
                handleArray(jsonReader);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Entity(null, itemsBuilder.getItems());
    }

    private void handleArray(JsonReader jsonReader) throws IOException {
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            handleObject(jsonReader);
        }
        jsonReader.endArray();
    }

    private void handleObject(JsonReader jsonReader) throws IOException {

        jsonReader.beginObject();
        String elementtag = jsonReader.nextName();
        itemsBuilder.openTag(elementtag);
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String element = jsonReader.nextName();
            itemsBuilder.openTag(element);
            String value = jsonReader.nextString();
            itemsBuilder.setElementValue(value);
            itemsBuilder.closeTag(element);
        }
        jsonReader.endObject();
        itemsBuilder.closeTag(elementtag);
        jsonReader.endObject();
    }
}
