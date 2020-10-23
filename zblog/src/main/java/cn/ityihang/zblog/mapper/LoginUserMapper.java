package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.model.LoginUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginUserMapper extends BaseMapper<LoginUser> {

    @Select("select * from sys_user where username = #{username}")
    LoginUser getUserByName(@Param("username") String username);
}
