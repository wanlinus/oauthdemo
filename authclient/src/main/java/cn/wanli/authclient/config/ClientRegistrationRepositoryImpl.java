package cn.wanli.authclient.config;

import cn.wanli.authclient.domain.Oauth2Server;
import cn.wanli.authclient.persistence.ClientRegistrationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 维护ClientRegistration, 及客户端注册信息
 */
//@Repository
public class ClientRegistrationRepositoryImpl implements ClientRegistrationRepository {

    @Resource
    private ClientRegistrationMapper mapper;

    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        LambdaQueryWrapper<Oauth2Server> query = new LambdaQueryWrapper<Oauth2Server>().eq(Oauth2Server::getRegistrationId, registrationId);
        Oauth2Server server = mapper.selectOne(query);
        return ClientRegistration.withRegistrationId("app")
                //客户端标识符
                .clientId("client_id")
                // 客户端密码
                .clientSecret("client_secret")
                // 用于使用Provider对客户端进行身份验证的方法
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                // OAuth 2.0授权框架定义了四种授权授权类型
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/api/authorized")
                .scope("read")
                .authorizationUri("http://127.0.0.1:8000/oauth2/authorize")
                .tokenUri("http://127.0.0.1:8000/oauth2/token")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .clientName("App")
                .build();
    }
}
