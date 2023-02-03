package com.mybroker.common.OAuth.vo;

import lombok.Data;

@Data
public class AccessToken {

    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expired_in;
    private String scope;

}
