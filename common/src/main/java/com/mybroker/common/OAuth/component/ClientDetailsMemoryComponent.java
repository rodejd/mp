package com.mybroker.common.OAuth.component;

import com.exchange.database.account.oauth.entity.OauthClientDetails;
import com.exchange.database.account.oauth.repository.OauthClientDetailsRepository;
import com.exchange.oauth.api.dto.SiteAuthClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * 주기적으로 Oauth Client 정보를 읽어서 Upload 하는 Component
 */
@Slf4j
@Component
public class ClientDetailsMemoryComponent {

    private static final HashMap<String, SiteAuthClient> clientDetailsMemoryMap = new HashMap<>();

    private final OauthClientDetailsRepository oauthClientDetailsRepository;

    private final static Object _lockMemoryUpload = new Object();

    @Autowired
    public ClientDetailsMemoryComponent(OauthClientDetailsRepository oauthClientDetailsRepository) {
        this.oauthClientDetailsRepository = oauthClientDetailsRepository;
    }


    public static SiteAuthClient getSiteAuthClientDetails(String clientId) {
        synchronized (_lockMemoryUpload) {
            return clientDetailsMemoryMap.get(clientId);
        }
    }
}
