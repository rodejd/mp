package com.mybroker.front.api.controller;

import com.mybroker.front.api.dto.CommonRes;
import com.mybroker.front.api.services.CommonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class CommonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonRes callService(HttpServletRequest request, CommonService service){

        CommonRes commonRes = new CommonRes();
        logger.info("TEST");

        return commonRes;
    }


}
