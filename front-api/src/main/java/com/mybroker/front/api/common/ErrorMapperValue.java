package com.mybroker.front.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorMapperValue {

    @JsonIgnore
    private int code = 0;
    @JsonIgnore
    private String desc = null;
    @JsonIgnore
    private String message_p = null;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public String getMessage_p() {
        return message_p;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }



}
