package com.friday.service.impl;

import com.friday.enumeration.SpecialWordsEnum;
import com.friday.exception.AddressNullException;
import com.friday.exception.RegexFormatException;
import com.friday.model.StreetAddressModel;
import com.friday.service.AddressParserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
class AddressParserServiceImpl implements AddressParserService {

    @Value("${address.regex}")
    public String regex ;

    @Value("${address.regex.no.keyword}")
    public String regexNoKeyword;

    /**
     * This function splits an address line like for example "Calle 39 No 1540" into its individual parts.
     * Supported parts are streetName, houseNumber.
     * Unit tests for testing the regular expression that this function uses exist over at https://regex101.com/r/2i96JP/2.
     * @param inputAddress
     * @return parsed String
     */

    public String parse (final String inputAddress) throws AddressNullException, RegexFormatException {
        StreetAddressModel streetModel = null;

        if (StringUtils.isEmpty(inputAddress)) {
            throw new AddressNullException("Address can not be null");
        }


        streetModel = this.matchPattern(inputAddress, streetModel);
        if (ObjectUtils.isEmpty(streetModel)) {
            throw new RegexFormatException("Format is not supported");
        }

        return streetModel.toString();
    }

    private StreetAddressModel matchPattern(final String inputAddress, StreetAddressModel streetModel) {
        Pattern pattern;
        if (inputAddress.contains(SpecialWordsEnum.WORD_NO_RULE.getRule())) {
            pattern = Pattern.compile(regexNoKeyword);
        } else {
            pattern = Pattern.compile(regex);
        }

        final Matcher matcher = pattern.matcher(inputAddress);
        if (matcher.find()) {
            streetModel = StreetAddressModel.builder().streetName(matcher.group(1)).
                    streetNumber(matcher.group(2)).build();
        }

        return streetModel;
    }
}
