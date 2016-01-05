package com.ej.router;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.builder.SpringApplicationBuilder;

import static org.junit.Assert.assertNotNull;

/**
 * Test for Application  start class.
 */
public class ApplicationTest {

    private Application application;

    @Before
    public void SetUp() {

        application = new Application();
    }

    @Test(expected = Exception.class)
    public void testMain() throws Exception {
        Application.main(new String[]{"just for test"});
    }

    @Test
    public void testConfigureMethod() {
        assertNotNull(application.configure(new SpringApplicationBuilder()));
    }

}
