package com.esrichina.geoservices.constant;

/**
 * 系统常量
 */
public enum ApplicationConstant {


    BASE_DICTIONARY("BASE_DICTIONARY", "BASE_DICTIONARY");

    private String name;
    private String value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private ApplicationConstant(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private ApplicationConstant() {
    }
}
