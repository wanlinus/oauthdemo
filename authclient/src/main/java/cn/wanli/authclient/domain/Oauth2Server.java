package cn.wanli.authclient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_oauth2_server")
public class Oauth2Server {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String registrationId;

    private String clientId;

    private String clientSecret;

    private String redirectUrl;
}
