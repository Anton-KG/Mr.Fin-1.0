package com.ej.sh1rk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class XmlHeaderTest {
    @Test
    public void testVersion() {
        XmlHeader xmlHeader = new XmlHeader(1, true, "UTF-8");

        assertNotNull(xmlHeader);
        assertEquals(xmlHeader.getXmlVersion(), 1, 1);
        assertTrue(xmlHeader.isStandAlone());
        assertEquals(xmlHeader.getEncoding(), "UTF-8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalVersion() {
        new XmlHeader(0.9, true, "UTF-8");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullEnconing() {
        new XmlHeader(2.0, true, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyEnconing() {
        new XmlHeader(2.0, true, "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSpaceEnconing() {
        new XmlHeader(2.0, true, "    ");
    }
}
