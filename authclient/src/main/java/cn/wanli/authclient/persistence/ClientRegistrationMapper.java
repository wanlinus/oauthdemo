package cn.wanli.authclient.persistence;

import cn.wanli.authclient.domain.Oauth2Server;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClientRegistrationMapper extends BaseMapper<Oauth2Server> {
}
