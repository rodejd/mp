package com.mybroker.front.api.controller;

import com.mybroker.front.api.dto.CommonRes;
import com.mybroker.front.api.services.CommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class ProductController extends CommonController {


    public CommonRes test(HttpServletRequest request){


        CommonService commonService = new CommonService();
        CommonRes res;

        res = callService(request, commonService);

        return res;
    }

}
