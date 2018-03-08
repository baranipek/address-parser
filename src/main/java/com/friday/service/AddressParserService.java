package com.friday.service;


import com.friday.exception.AddressNullException;
import com.friday.exception.RegexFormatException;

public interface AddressParserService {

    String parse(String input) throws AddressNullException, RegexFormatException;
}
