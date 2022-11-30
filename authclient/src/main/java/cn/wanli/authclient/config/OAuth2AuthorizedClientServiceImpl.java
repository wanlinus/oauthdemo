package cn.wanli.authclient.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;

import javax.annotation.Resource;

/**
 * 维护 OAuth2AuthorizedClient 即 ClientRegistration关联 AccessToken 等信息
 */
//@Component
public class OAuth2AuthorizedClientServiceImpl implements OAuth2AuthorizedClientService {

    @Resource
    private ClientRegistrationRepository clientRegistrationRepository;


    @Override
    public <T extends OAuth2AuthorizedClient> T loadAuthorizedClient(String clientRegistrationId, String principalName) {
        return null;
    }

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, Authentication principal) {

    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {

    }
}
