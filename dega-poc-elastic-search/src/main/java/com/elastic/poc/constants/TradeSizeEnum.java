package com.elastic.poc.constants;

public enum TradeSizeEnum {

    MINI("25KB"),
    SMALL("100KB"),
    MEDIUM("500KB"),
    LARGE("1MB"),
    LARGEST("2MB")
    ;

    TradeSizeEnum(String tradeSize) {
    }
}
