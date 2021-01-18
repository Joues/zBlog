package cn.ityihang.zblog.system.service;

import cn.ityihang.zblog.system.entity.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ILoginUserService extends IService<LoginUser> {
    LoginUser getUserByName(String username);
}
