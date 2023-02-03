package com.mybroker.common.OAuth.component;

import com.exchange.oauth.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * google reCaptcha 처리 서비스.
 */
@Service
public class RecaptchaComponent {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${site.google.recaptcha.secret}")
    private String recaptchaSecret;

    private static final String GOOGLE_RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";

    public static final Map<String, String> RECAPTCHA_ERROR_CODE = new HashMap<>();

    static {

        RECAPTCHA_ERROR_CODE.put("missing-input-secret", "The secret parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-secret", "The secret parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("missing-input-response", "The response parameter is missing");
        RECAPTCHA_ERROR_CODE.put("invalid-input-response", "The response parameter is invalid or malformed");
        RECAPTCHA_ERROR_CODE.put("bad-request", "The request is invalid or malformed");
    }

    private final RestTemplate restTemplate;

    @Autowired
    public RecaptchaComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean verifyRecaptcha(String mbId, String recaptchaResponse) throws ServiceException {

        return true;

        /*boolean recaptchaSuccess = false;

        ResponseEntity<String> response;

        logger.debug("[ call Google reCAPTCHA ] ============================================ ");
        logger.debug("[ url ] {} ", GOOGLE_RECAPTCHA_VERIFY_URL);
        logger.debug("[ call Google reCAPTCHA ] ============================================ ");

        Map<String, String> param = new HashMap<>();
        param.put("secret", recaptchaSecret);
        param.put("response", recaptchaResponse);

        try {

            logger.error("Request Body for recaptcha = {} ", param.toString());

            ResponseEntity<Map> recaptchaResponseEntity = restTemplate.postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL + "?secret={secret}&response={response}", param, Map.class, param);
            Map<String, Object> responseBody = recaptchaResponseEntity.getBody();

            logger.error("{} google recapcha call Error errorMessage = {}", mbId, recaptchaResponseEntity.getBody().toString());
            recaptchaSuccess = (Boolean) responseBody.get("success");

            if (!recaptchaSuccess) {
                List<String> errorCode = (List) responseBody.get("error-codes");
                String errorMessage = errorCode.stream()
                        .map(s -> RECAPTCHA_ERROR_CODE.get(s))
                        .collect(Collectors.joining(", "));

                logger.error(" {} google recapcha call Error errorMessage = {}", mbId, errorMessage);
                throw new ServiceException(ErrorEnum.NETWORK_ERROR_BALANCE);
            }

        } catch (HttpClientErrorException e) {
            //log.error("",e);
            logger.info("{} google recapcha call Error statusCode = {} ", mbId, e.getStatusCode());
            throw new ServiceException(ErrorEnum.NETWORK_ERROR_BALANCE);
        } catch (Exception e) {
            //log.error("",e);
            logger.info("{} google recapcha call Error statusCode = {} ", mbId);
            throw new ServiceException(ErrorEnum.NETWORK_ERROR_BALANCE);
        }
        return recaptchaSuccess;*/
    }
}
