package proj.esso.MemManagement.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Index.class)
class IndexIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void indexDefault() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get( "/v1/api/");
        MvcResult result = mvc.perform(req).andReturn();
        assertEquals( "input = jorje", result.getResponse().getContentAsString());
    }

    @Test
    void indexParam() throws Exception {
        RequestBuilder req = MockMvcRequestBuilders.get("/v1/api/?param=reginaldo");
        MvcResult result = mvc.perform(req).andReturn();
        assertEquals("input = reginaldo", result.getResponse().getContentAsString());
    }
}