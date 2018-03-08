package com.friday.enumeration;


public enum SpecialWordsEnum {

    WORD_NO_RULE("No");

    private String rule;

    SpecialWordsEnum(String rule) {
        this.rule = rule;
    }

    public String getRule() {
        return rule;
    }
}
