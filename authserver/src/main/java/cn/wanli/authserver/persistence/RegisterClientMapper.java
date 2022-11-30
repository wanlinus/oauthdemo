package cn.wanli.authserver.persistence;

import cn.wanli.authserver.domain.RegisterClient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterClientMapper extends BaseMapper<RegisterClient> {
}
