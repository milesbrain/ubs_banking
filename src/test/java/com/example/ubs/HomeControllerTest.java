package com.example.ubs;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class HomeControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }

    @Test
    public void testHome() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void testOpenAccount() throws Exception {
        mockMvc.perform(get("/open_account"))
                .andExpect(status().isOk())
                .andExpect(view().name("open_account"));
    }

    @Test
    public void testAddress() throws Exception {
        mockMvc.perform(get("/address"))
                .andExpect(status().isOk())
                .andExpect(view().name("Address"));
    }
}
