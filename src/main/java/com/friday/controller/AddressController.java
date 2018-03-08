package com.friday.controller;

import com.friday.exception.AddressNullException;
import com.friday.exception.RegexFormatException;
import com.friday.service.AddressParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/address/parse",consumes = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    private final AddressParserService parserService;

    @Autowired
    public AddressController(AddressParserService parserService) {this.parserService = parserService;}

    @PostMapping
    String parse (@RequestBody String address) throws RegexFormatException, AddressNullException {
        return parserService.parse(address);
    }
}
