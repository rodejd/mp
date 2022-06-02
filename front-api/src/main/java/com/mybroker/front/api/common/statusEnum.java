package com.mybroker.front.api.common;

public enum statusEnum {


    SUCCESS("0", "SUCCESS"),
    ERROR("9999", "ERROR"),
    FAIL("9999", "FAIL");

    private final String status;
    private final String message;


    statusEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }


    public String getStatus() {return status; }
    public String getMessage() {return message; }



}
