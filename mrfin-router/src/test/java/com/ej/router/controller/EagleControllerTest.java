package com.ej.router.controller;

import com.ej.router.exceptions.CacheResponseException;
import com.ej.router.services.ConvertService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Test class for {@link com.ej.router.controller.EagleController} class.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
public class EagleControllerTest {
    private ConvertService dummyConvertService;
    private EagleController controller;
    private MockMvc mvc;

    @Before
    public void setUp() {
        dummyConvertService = mock(ConvertService.class);
        when(dummyConvertService.convert(anyObject())).thenReturn(new BigDecimal(150));

        controller = new EagleController();
        ReflectionTestUtils.setField(controller, "convertService", dummyConvertService);

        mvc = standaloneSetup(controller).build();
    }

    @Test
    public void testConvert() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(equalTo("{\"convertedAmount\":\"150\",\"message\":\"OK\",\"status\":\"200\"}")));
    }

    @Test
    public void testConvert2() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void testConvert3() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/-100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(equalTo("{\"convertedAmount\":\"\",\"message\":\"Invalid arguments\",\"status\":\"400\"}")));
    }

    @Test
    public void testConvert4() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/i100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(equalTo("{\"convertedAmount\":\"\",\"message\":\"Invalid arguments in request\",\"status\":\"400\"}")));
    }

    @Test
    public void testConvert5() throws Exception {
        ConvertService dummyConvertService = mock(ConvertService.class);
        when(dummyConvertService.convert(anyObject())).thenThrow(RuntimeException.class);

        EagleController controller = new EagleController();
        ReflectionTestUtils.setField(controller, "convertService", dummyConvertService);

        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("500")));
    }

    @Test
    public void testConvert6() throws Exception {
        ConvertService dummyConvertService = mock(ConvertService.class);
        when(dummyConvertService.convert(anyObject())).thenThrow(CacheResponseException.class);

        EagleController controller = new EagleController();
        ReflectionTestUtils.setField(controller, "convertService", dummyConvertService);

        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/100").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("500")));
    }

    @Test
    public void testConvert7() throws Exception {
        ConvertService dummyConvertService = mock(ConvertService.class);
        when(dummyConvertService.convert(anyObject())).thenReturn(new BigDecimal(150));
        EagleController controller = new EagleController();
        ReflectionTestUtils.setField(controller, "convertService", dummyConvertService);

        MockMvc mvc = standaloneSetup(controller).build();

        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/100").header("user-agent", "").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("200")));

        mvc = standaloneSetup(controller).build();
        mvc.perform(MockMvcRequestBuilders.get("/convert/uah/usd/100").header("user-agent", "Mozilla Opera, Safari Ie TEST TES TTT").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("200")));
    }
}
