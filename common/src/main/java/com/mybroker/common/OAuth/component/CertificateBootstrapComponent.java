package com.mybroker.common.OAuth.component;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 서명된 토큰값을 생성하기 위한
 * Keystore file 의 password 와 alias 내용을 저장하고 있는 파일을
 * 최초 서버 기동시 읽어 static 변수에 저장한다.
 */
@Slf4j
@Component
public class CertificateBootstrapComponent {

    private static final Map<String, Object> keypassMap = new HashMap<>();

    @Value("${site.password-keypath}")
    private String certPasswordFilePath;

    @Value("${site.alias-keypath}")
    private String certAliasFilePath;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    public void init() {

        try (
                BufferedReader certPasswordReader = new BufferedReader(new InputStreamReader(new ClassPathResource(certPasswordFilePath).getInputStream()));
                BufferedReader certAliasReader = new BufferedReader(new InputStreamReader(new ClassPathResource(certAliasFilePath).getInputStream()))) {

            keypassMap.put("certAlias", certAliasReader.readLine());
            keypassMap.put("certPassword", certPasswordReader.readLine());

        } catch (Exception e) {
            log.error("", e);
        }
        logger.info(" keystore load success ! ");
    }

    public static Map<String, Object> getKeypassMap() {
        return keypassMap;
    }
}
