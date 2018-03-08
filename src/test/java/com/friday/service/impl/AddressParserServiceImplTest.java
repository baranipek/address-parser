package com.friday.service.impl;

import com.friday.exception.AddressNullException;
import com.friday.service.AddressParserService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@SpringBootConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class AddressParserServiceImplTest {

    @Autowired
    private AddressParserService parserService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Configuration
    static class Config {

        @Bean
        public AddressParserService getFileManagerService() {
            return new AddressParserServiceImpl();
        }

    }

    @Test
    public void shouldSplitStreetNameAndOneDigitNumber() throws Exception{
        String result = parserService.parse("Winterallee 3");
        assertEquals("{“Winterallee”, “3”}", result);
    }

    @Test
    public void shouldSplitStreetNameAndTwoDigitNumber() throws Exception {
        String result = parserService.parse("Musterstrasse 45");
        assertEquals("{“Musterstrasse”, “45”}", result);
    }

    @Test
    public void shouldSplitStreetNameAndAlphaNumericStreetNumber() throws Exception {
        String result = parserService.parse("Blaufeldweg 123B");
        assertEquals("{“Blaufeldweg”, “123B”}", result);
    }

    @Test
    public void shouldSplitStreetNameAndStreetNumberWithKeywordNo() throws Exception {
        String result = parserService.parse("Calle 39 No 1540");
        assertEquals("{“Calle 39”, “No 1540”}", result);
    }

    @Test
    public void shouldSplitStreetNameWithGermanSpecialCharacters() throws Exception {
        String result = parserService.parse("Am Bächle 23");
        assertEquals("{“Am Bächle”, “23”}", result);
    }

    @Test
    public void shouldSplitStreetNameMultipleWordsWithAlphaNumericNumber() throws Exception {
        String result = parserService.parse("Auf der Vogelwiese 23 b");
        assertEquals("{“Auf der Vogelwiese”, “23 b”}", result);
    }


    @Test
    public void shouldThrowExceptionWhenAddressIsEmpty() throws Exception {
        thrown.expect(AddressNullException.class);
        thrown.expectMessage("Address can not be null");
        parserService.parse("");
    }

    @Test
    public void shouldSplitStreetNameMultipleWordsWithCommaAndAlphaNumericNumber() throws Exception {
        String result = parserService.parse("Auf der Vogelwiese, 23 b");
        assertEquals("{“Auf der Vogelwiese”, “23 b”}", result);
    }

    @Test
    public void shouldSplitStreetNameMultipleWordsWithCommaAndFractionStreetNumber() throws Exception {
        String result = parserService.parse("Main Square, 23/15");
        assertEquals("{“Main Square”, “23/15”}", result);
    }

}