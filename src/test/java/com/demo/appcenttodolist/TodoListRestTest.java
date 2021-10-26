package com.demo.appcenttodolist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoListRestTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testAuthentication() throws Exception{
        // Test authentication with correct credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"ferhat.fda8@gmail.com\", \"password\":\"123456\"}"))
                .andDo(print()).andExpect(status().isOk());

        // Test authentication with wrong credentials
        this.mockMvc.perform(post("/login")
                .content("{\"username\":\"ferhat.fda8@gmail.com\", \"password\":\"wrongpwd\"}"))
                .andDo(print()).andExpect(status().is4xxClientError());
    }
}
