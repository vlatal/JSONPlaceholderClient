package us.futurio.dev.jsonplaceholderclient.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ClientController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserController controller;

    @Test
    public void all() throws Exception {
        this.mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void one() throws Exception {
        this.mockMvc.perform(get("/users/{clientId}", 1)
            .accept(MediaType.APPLICATION_JSON))
            .andDo(print())
            .andExpect(status().isOk());
    }

    @Test
    public void oneBriefDetail() throws Exception {
        this.mockMvc.perform(get("/users/{clientId}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Leanne")))
                .andExpect(content().string(containsString("Howell")));
    }
}