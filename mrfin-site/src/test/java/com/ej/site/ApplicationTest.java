package com.ej.site;

import org.junit.Test;

/**
 * Test class for {@link Application} class.
 */
public class ApplicationTest {

    @Test(expected = Exception.class)
    public void ApplicationTest() {
        Application.main(new String[]{"--spring.output.ansi.enabled=always"});
    }
}
