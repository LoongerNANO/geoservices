package com.esrichina.geoservices.constant;

public enum LogConstant {


    INFO("INFO", "INFO"),
    WARN("WARN", "WARN"),
    ERROR("ERROR", "ERROR");

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

    private LogConstant(String name, String value) {
        this.name = name;
        this.value = value;
    }

    private LogConstant() {
    }
}
