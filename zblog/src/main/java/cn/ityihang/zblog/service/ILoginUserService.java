package cn.ityihang.zblog.service;

import cn.ityihang.zblog.model.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ILoginUserService extends IService<LoginUser> {
    LoginUser getUserByName(String username);
}
