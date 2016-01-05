package com.ej.router.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)

public class ErrorHandlerTest {
    private MockMvc mvc;

    @Before
    public void prepare() {
        ErrorHandler controller = new ErrorHandler();
        this.mvc = standaloneSetup(controller).build();
    }
    @Test
    public void testError() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/coonvert/USD/UAH/ji").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        mvc.perform(MockMvcRequestBuilders.get("/error").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string(equalTo("{\"convertedAmount\":\"\",\"message\":\"Sorry,the server couldn't " +
                        "accept such request, please,try again.\",\"status\":\"400\"}")));
    }
}
