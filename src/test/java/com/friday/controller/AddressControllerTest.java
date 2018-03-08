package com.friday.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.LOG_DEBUG)
public class AddressControllerTest {

    @Autowired
    private MockMvc mockModelView;

    @Test
    public void shouldSplitStreetNameMultipleWordsWithCommaAndAlphaNumericNumber() throws Exception {
        String result = this.mockModelView.perform(post("/address/parse")
                .contentType(MediaType.APPLICATION_JSON)
                .content("Auf der Vogelwiese 23 b"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful()).andReturn().getResponse().getContentAsString();

        assertEquals("{“Auf der Vogelwiese”, “23 b”}", result);

    }

    @Test
    public void shouldThrowIsBadRequestExceptionWhenAddressIsEmpty() throws Exception {

         this.mockModelView.perform(post("/address/parse")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
    }


}