package com.ej.sh1rk;

import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.transformer.JsonTransformer;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JsonTransformerTest {
    private static List<Item> items = new ArrayList<>();

    @BeforeClass
    public static void setUp() {
        Item item = new Item("book")
                .withAttribute("status", "onsale")
                .with("author", "John Stevens")
                .with("genre", "Winter Chronicles");

        items.add(item);
    }

    @Test
    public void noPretty() throws Exception {
        JsonTransformer jt = new JsonTransformer(new Entity(null, items));
        String noPretty = loadFile("/nopretty.json");
        assertEquals(jt.build(), noPretty);
    }

    @Test
    public void isPretty() throws Exception {
        JsonTransformer jt = new JsonTransformer(new Entity(null, items));
        jt.prettyPrint();
        String isPretty = loadFile("/ispretty.json");
        assertEquals(jt.build(), isPretty);
    }

    private String loadFile(String pathFile) throws Exception{
        File fileNoPretty = new File(this.getClass().getResource(pathFile).toURI());
        StringBuilder sb = new StringBuilder();

        try(BufferedReader in = new BufferedReader(new FileReader(fileNoPretty))) {
            String str;
            while ((str = in.readLine()) != null) {
                if(sb.length() != 0) {
                    sb.append("\n");
                }

                sb.append(str);
            }
        }

        return sb.toString();
    }
}
