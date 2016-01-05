import com.ej.cache.MainContainer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Test class for the Cache layer of Mr.Fin Framework.
 *
 */
@ContextConfiguration(classes = MainContainer.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainContainer.class)
@WebAppConfiguration
public class SkylarkControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype());

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void prepare() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testFromUAH() throws Exception {
        mockMvc.perform(get("/retrieve/UAH/USD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void testToUAH() throws Exception {
        mockMvc.perform(get("/retrieve/USD/UAH"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void testThroughUAH() throws Exception {
        mockMvc.perform(get("/retrieve/EUR/USD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType));
    }

    @Test
    public void testFailed() throws Exception {
        mockMvc.perform(get("/retrieve/EUR/UAD"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.Status", is(400)));
    }

}
