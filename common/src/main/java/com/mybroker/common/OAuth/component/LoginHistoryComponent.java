package com.mybroker.common.OAuth.component;

import com.exchange.database.account.account_info.entity.MemberLoginHis;
import com.exchange.database.account.account_info.repository.MemberLoginHisRepository;
import com.exchange.database.account.account_info.repository.MemberRepository;
import com.exchange.database.redis.repository.BeanUserRepository;
import com.exchange.oauth.api.common.ErrorEnum;
import com.exchange.oauth.api.common.OauthApiUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class LoginHistoryComponent {

    @Value("${site.email-domain}")
    private String EMAIL_DOMAIN;

    private final MemberLoginHisRepository memberLoginHisRepository;
    private final BeanUserRepository beanUserRepository;
    private final MemberRepository memberRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public LoginHistoryComponent(MemberLoginHisRepository memberLoginHisRepository, BeanUserRepository beanUserRepository, MemberRepository memberRepository, RestTemplate restTemplate) {
        this.memberLoginHisRepository = memberLoginHisRepository;
        this.beanUserRepository = beanUserRepository;
        this.memberRepository = memberRepository;
        this.restTemplate = restTemplate;
    }


    @Async
    public void _insertSuccessLoginLog(String clientId, int mbNo, String mbId, String ip, String agent, String mbStatus, String parseUserAgent, String mbUseLanguage) {

        LocalDateTime loginTime = LocalDateTime.now(Clock.systemUTC());
        MemberLoginHis loginHis = MemberLoginHis.builder()
                .clientId(clientId)
                .mbNo(mbNo)
                .mbId(mbId)
                .mbKey("")
                .oauthResultCode(0)
                .oauthResultMsg(ErrorEnum.LOGIN_SUCCESS.getDesc())
                .loginAgent(agent)
                .loginUserOs(OauthApiUtils.getUserOs(agent))
                .loginUserBrowser(OauthApiUtils.getUserBrowser(agent))
                .loginBlockYn("N")
                .loginRegDt(LocalDateTime.now())
                .loginRegIp(ip)
                .build();

        memberLoginHisRepository.saveAndFlush(loginHis);

        int result;
        if ("REST".equals(mbStatus)) {
            result = memberRepository.updateRestMemberLogin(mbNo);
        } else {
            result = memberRepository.updateMemberLogin(mbNo);
        }
        log.debug(" {} login info update result = ({})  ", mbId, result);

        try {

            Map<String, Object> param = new HashMap<>();
            param.put("mbId", mbId);
            param.put("regDt", loginTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

            ResponseEntity<String> response;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(new Gson().toJson(param), headers);
            response = restTemplate.exchange(EMAIL_DOMAIN + "/ems/user/complete-login", HttpMethod.POST, entity, String.class);

            log.info("{} Email-Sender response == [End] {}", mbId, response.getBody());

        } catch (HttpClientErrorException e) {
            log.error("{} Email-Sender call Error statusCode = {} ", mbId, e.getStatusCode());
        } catch (Exception e) {
            log.error("{} Email-Sender call Error {}", mbId, e.getMessage(), e);
        }
    }


    /**
     * 비밀번호 오류 입력과 로그인 성공시 로그인 히스토리 기록을 남긴다.
     */
    @Async
    public int _insertFailLoginLog(ErrorEnum errorEnum, String clientId, int mbNo, String mbId, String ip, String agent) {

        int warncnt = beanUserRepository.getWarnCountRedis(mbId);

        String isBlock = "N";
        if (warncnt > 3) {
            isBlock = "Y";
        }

        if ("0".equals(errorEnum.getStatus())) {
            if (warncnt > 0) beanUserRepository.delWarnCountRedis(mbId);
        } else {
            beanUserRepository.setWarnCountRedis(mbId, warncnt);
        }

        MemberLoginHis loginHis = MemberLoginHis.builder()
                .clientId(clientId)
                .mbNo(mbNo)
                .mbId(mbId)
                .mbKey("")
                .oauthResultCode(Integer.parseInt(errorEnum.getStatus()))
                .oauthResultMsg(errorEnum.getDesc())
                .loginAgent(agent)
                .loginUserOs(OauthApiUtils.getUserOs(agent))
                .loginUserBrowser(OauthApiUtils.getUserBrowser(agent))
                .loginBlockYn(isBlock)
                .loginRegDt(LocalDateTime.now())
                .loginRegIp(ip)
                .build();

        memberLoginHisRepository.saveAndFlush(loginHis);

        return warncnt;
    }
}
