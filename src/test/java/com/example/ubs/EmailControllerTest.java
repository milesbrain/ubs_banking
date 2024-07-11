package com.example.ubs;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class EmailControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EmailController emailController;

    @Mock
    private Email emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }

    @Test
    public void testSendEmail() throws Exception {
        EmailRequest request = new EmailRequest("test@example.com", "Test Content");
        ObjectMapper objectMapper = new ObjectMapper();

        mockMvc.perform(post("/send-email")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("Email sent successfully"));

        verify(emailService, times(1)).send("test@example.com", "Test Content");
    }
}
