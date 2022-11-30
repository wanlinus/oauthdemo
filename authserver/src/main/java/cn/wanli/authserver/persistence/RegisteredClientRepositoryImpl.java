package cn.wanli.authserver.persistence;

import cn.wanli.authserver.domain.RegisterClient;
import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 用于第三方认证，RegisteredClientRepository 主要用于管理第三方（每个第三方就是一个客户端）
 *
 * @author wanli
 */
@Slf4j
@Repository
public class RegisteredClientRepositoryImpl implements RegisteredClientRepository {

    @Resource
    private RegisterClientMapper registerClientMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {
        RegisterClient client = new RegisterClient();
        client.setClientId(registeredClient.getClientId());
        client.setClientSecret(registeredClient.getClientSecret());
        client.setClientName(registeredClient.getClientName());
        client.setClientIdIssuedAt(registeredClient.getClientIdIssuedAt());
        client.setClientSecretExpiresAt(registeredClient.getClientSecretExpiresAt());
        client.setRedirectUrl(String.join(",", registeredClient.getRedirectUris()));
        client.setScopes(String.join(",", registeredClient.getScopes()));
        registerClientMapper.insert(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        RegisterClient client = registerClientMapper.selectById(Long.parseLong(id));
        return getRegisteredClient(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        LambdaQueryWrapper<RegisterClient> queryWrapper = new LambdaQueryWrapper<RegisterClient>()
                .eq(RegisterClient::getClientId, clientId);
        RegisterClient client = registerClientMapper.selectOne(queryWrapper);
        return getRegisteredClient(client);
    }

    private RegisteredClient getRegisteredClient(RegisterClient client) {
        log.info("client: [{}]", JSON.toJSONString(client));
        if (Objects.isNull(client)) {
            return null;
        }
        return RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(passwordEncoder.encode(client.getClientSecret()))
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .redirectUris(uris -> uris.addAll(List.of(client.getRedirectUrl().split(","))))
                .scope(OidcScopes.OPENID)
                .scopes(scopes -> scopes.addAll(List.of(client.getScopes().split(","))))
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .build();
    }
}
