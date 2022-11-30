package cn.wanli.authclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * spring security oauth2 配置
 * CommonOAuth2Provider
 */
@Configuration
public class OauthClientConfig {

    @Bean
    public SecurityFilterChain chain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }

}
