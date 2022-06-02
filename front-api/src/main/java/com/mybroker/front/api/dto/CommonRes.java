package com.mybroker.front.api.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class CommonRes {


    private String status;

    private String message;

    private Object data;


}
