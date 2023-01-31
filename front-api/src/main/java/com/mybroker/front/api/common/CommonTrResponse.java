package com.mybroker.front.api.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Data
@ToString
public class CommonTrResponse {

    //  funcKey는 삭제처리
    @JsonIgnore
    private String funcKey;
    @JsonIgnore
    private ErrorMapperValue errorMapperValue;

    private String funcName;
    private String status;
    private String message;

    public void setError(ErrorMapperValue errorMapperValue) {
        this.errorMapperValue = errorMapperValue;
        this.status = String.valueOf(errorMapperValue.getCode());
    }

    public void setSuccess() {
//        ErrorMapperValue errorMapperValue = new ErrorMapperValue(ErrorResult.RESULT_OK);
        this.errorMapperValue = errorMapperValue;
        this.status = String.valueOf(errorMapperValue.getCode());
        this.message = String.valueOf(errorMapperValue.getDesc());
    }

    // TCP status 값을 파싱하여 message에 넣는 함수
    public void changeTcpErrorStatus() {
        this.status = errorMapperValue.getCode() + "";
    }


    /**
     * 사용자의 언어에 맞게끔 Message 셋팅함수
     **/
    public void setLangMessage(MessageSource messageSource) throws Exception {
        Locale locale = LocaleContextHolder.getLocale();
        this.message = messageSource.getMessage(errorMapperValue.getMessage_p(), null, locale);
        errorMapperValue = null;
    }


}
