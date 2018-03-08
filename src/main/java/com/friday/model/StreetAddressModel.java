package com.friday.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreetAddressModel {

    private String streetName;
    private String streetNumber;

    @Override
    public String toString() {
        final StringBuffer result = new StringBuffer();
        result.append("{“");
        result.append(streetName.replaceAll(",", "").trim()).append("”, ").append("“");
        result.append(streetNumber.trim()).append("”}");
        return result.toString();
    }

}
