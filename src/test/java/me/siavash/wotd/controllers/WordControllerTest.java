package me.siavash.wotd.controllers;

import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class WordControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void shouldReturnNothing() throws Exception {
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/api/word"))
        .andExpect(status().isOk());
  }
}
