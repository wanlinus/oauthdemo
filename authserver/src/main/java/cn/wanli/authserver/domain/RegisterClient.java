package cn.wanli.authserver.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_register_client")
public class RegisterClient extends Model<RegisterClient> {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String clientId;

    private String clientSecret;

    private String redirectUrl;

    private String clientName;

    /**
     * 发布日期
     */
    private Instant clientIdIssuedAt;

    /**
     * secret过期时间
     */
    private Instant clientSecretExpiresAt;

    private String scopes;


}
