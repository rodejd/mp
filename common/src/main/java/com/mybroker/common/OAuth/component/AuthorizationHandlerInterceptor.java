package com.mybroker.common.OAuth.component;

import com.exchange.database.redis.entity.RefreshTokenInfo;
import com.exchange.database.redis.repository.RefreshTokenInfoRepository;
import com.exchange.database.redis.service.TokenCheckFailException;
import com.exchange.oauth.api.common.Constants;
import com.exchange.oauth.api.common.ErrorEnum;
import com.exchange.oauth.api.common.OauthApiUtils;
import com.exchange.oauth.api.config.oauth.OAuthTokenReqValidator;
import com.exchange.oauth.api.dto.OAuthTokenReq;
import com.exchange.oauth.api.dto.reqeust.JwtRefreshToken;
import com.exchange.oauth.api.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.CaseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;

@Slf4j
@Service
public class AuthorizationHandlerInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OAuthTokenReqValidator oAuthTokenReqValidator;
    private final RefreshTokenInfoRepository refreshTokenInfoRepository;

    @Autowired
    public AuthorizationHandlerInterceptor(OAuthTokenReqValidator oAuthTokenReqValidator, RefreshTokenInfoRepository refreshTokenInfoRepository) {
        this.oAuthTokenReqValidator = oAuthTokenReqValidator;
        this.refreshTokenInfoRepository = refreshTokenInfoRepository;
    }

    /**
     * 컨트롤러 전에 요청 파라미터의 유효성을 검증
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {


        // OauthTokenReq Parameter Setting
        HashMap<String, String> parameterMap = new HashMap<>();
        request.getParameterMap().forEach((k, v) -> {
            parameterMap.put(CaseUtils.toCamelCase(k, false, '_'), Arrays.toString(v).replace("[", "").replace("]", ""));
        });

        OAuthTokenReq req = new ObjectMapper().convertValue(parameterMap, OAuthTokenReq.class);
        String uaHeader = OauthApiUtils.getUserAgent(request);

        logger.info("=======================================================================================");
        logger.info("[start] {} ua=({})", req.toString(), uaHeader);
        logger.info("=======================================================================================");

        // 객체 검증
        BindingResult bindingResult = new BeanPropertyBindingResult(req, "OAuthTokenReq");
        oAuthTokenReqValidator.validate(req, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ServiceException(ErrorEnum.VALIDATOR_REQUIRED);
        }

        // 토큰 갱신 요청인 경우 기존 토큰이 유효한지 체크한다.
        if (Constants.GRANT_TYPE_REFRESH_TOKEN.equals(req.getGrantType()) && !_refreshTokenValidation(req.getRefreshToken(), uaHeader)) {
            throw new TokenCheckFailException();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

    /**
     * Refresh Token 이 유효한지 확인한다.
     */
    private boolean _refreshTokenValidation(String refreshToken, String uaHeader) {

        boolean valid = false;

        try {

            String[] splitString = refreshToken.split("\\.");
            if (splitString.length != 3) {
                throw new ServiceException(ErrorEnum.INVALID_REFRESH_TOKEN);
            }

            Base64 base64 = new Base64(true);
            String body = new String(base64.decode(splitString[1]));

            Gson gson = new Gson();
            JwtRefreshToken jwt = gson.fromJson(body, JwtRefreshToken.class);

            logger.info(uaHeader);
            logger.info(jwt.getMbNo());

            RefreshTokenInfo refreshTokenInfo = refreshTokenInfoRepository.getRefreshToken(uaHeader, jwt.getMbNo(), jwt.getJti());

            if (refreshTokenInfo != null) {
                valid = true;
            }

        } catch (Exception e) {
            log.error("", e);
            return false;
        }
        return valid;
    }
}
