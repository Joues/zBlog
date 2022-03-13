package cn.ityihang.zblog.system.mapper;

import cn.ityihang.zblog.system.entity.SysUserDetails;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface SysUserDetailsMapper extends BaseMapper<SysUserDetails> {

}