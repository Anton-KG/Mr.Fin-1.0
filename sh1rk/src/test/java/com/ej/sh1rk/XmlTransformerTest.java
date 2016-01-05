package com.ej.sh1rk;

import com.ej.sh1rk.data.Item;
import com.ej.sh1rk.exceptions.Sh1rkSyntaxException;
import com.ej.sh1rk.exceptions.Sh1rkTransformerException;
import com.ej.sh1rk.transformer.Header;
import com.ej.sh1rk.transformer.Transformer;
import com.ej.sh1rk.transformer.XmlTransformer;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Class XmlTransformerTest implements tests  for {@link XmlTransformer}.
 */
public class XmlTransformerTest {

    private Transformer transformer;
    private Transformer transformerPretty;
    private XmlTransformer transformerWithHeader;
    private Transformer transformerEmptyItems;
    private XmlTransformer xmlTransformerForParent;

    @Before
    public void setUp() {
        List<Item> dummyItems = new ArrayList<>();
        dummyItems.add(new Item("first").with("client", "Tomas"));
        dummyItems.add(new Item("second"));
        dummyItems.add(new Item("third"));
        dummyItems.add(new Item("fourth").withAttribute("gender", "male"));
        Entity entity = new Entity(new XmlHeader(2.0, true, "UTF-8"), dummyItems);
        transformer = new XmlTransformer(entity);
        transformerPretty = new XmlTransformer(entity);
        transformerWithHeader = new XmlTransformer(entity);
        transformerWithHeader.withXmlHeader(Header.STANDART);
        transformerWithHeader.withXmlHeader(1.0, false, "UTF-8");
        transformerEmptyItems = new XmlTransformer(new Entity(null, new ArrayList<>()));

        List<Item> listForParent = new ArrayList<>();
        listForParent.add(new Item("book1").withAttribute("status", "onsale").with("author", "John Stevens"));
        listForParent.add(new Item("book2").with("author", "Bloh Smeath"));
        xmlTransformerForParent = new XmlTransformer(new Entity(new XmlHeader(1.0, true, "UTF-8"), listForParent));
    }

    @Test
    public void testBuild() {
        assertNotNull("build entity couldn't be null", transformer.build());
        assertNotNull("build entity couldn't be null", transformerPretty.prettyPrint().build());
    }

    @Test
    public void testRandom() {
        assertNotNull("After random  items should be shuffled",transformer.random().build());
    }

    @Test
    public void testPrettyPrint() {
        assertNotEquals("The field prettyPrint should be true",
                transformerPretty.build(), transformerPretty.prettyPrint().build());
    }

    @Test
    public void testwithXmlHeader() {
        assertNotEquals("String results with XmlHeader and with different XmlHeaders should n't be equals",
                transformerWithHeader.build(), transformerWithHeader.withXmlHeader(2.0, false, "UTF-32").build());
    }

    @Test(expected = Sh1rkTransformerException.class)
    public void testSh1rkTransformerExceptionByBuildMethod() {
        transformerEmptyItems.build();
    }

    @Test(expected = Sh1rkTransformerException.class)
    public void testSh1rkTransformerExceptionByWithXmlHeaderMethod() {
        transformerWithHeader.withXmlHeader(null);
    }

    @Test
    public void testParent() {


        xmlTransformerForParent.addParent("Books");

        String result = "<?xml version=\"1.0\" standalone=\"true\" encoding=\"UTF-8\"?>" +
                "<Books>" +
                "<book1 status=\"onsale\"><author>John Stevens</author></book1>" +
                "<book2><author>Bloh Smeath</author></book2>" +
                "</Books>";

        assertEquals(xmlTransformerForParent.build(), result);
    }

    @Test
    public void testParentAndPrettyPrint() {
        xmlTransformerForParent.prettyPrint().addParent("Books");

        String result = "<?xml version=\"1.0\" standalone=\"true\" encoding=\"UTF-8\"?>\n" +
                "<Books>\n" +
                "    <book1 status=\"onsale\">\n" +
                "        <author>John Stevens</author>\n" +
                "    </book1>\n" +
                "    <book2>\n" +
                "        <author>Bloh Smeath</author>\n" +
                "    </book2>\n" +
                "</Books>";

        assertEquals(xmlTransformerForParent.build(), result);
    }

    @ Test(expected = Sh1rkSyntaxException.class)
    public void testParentToIllegalNameTest(){
        char illegalSymbol = '&';
        String str = Sh1rk.create(new Item("secondItem").with("author", "secondAuthor").withAttribute("attr2", "some attr 2"))
                .toXML().addParent("Books"+illegalSymbol).build();
    }
}
