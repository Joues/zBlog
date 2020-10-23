package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.mapper.LoginUserMapper;
import cn.ityihang.zblog.model.LoginUser;
import cn.ityihang.zblog.service.ILoginUserService;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @Author: yihang
 * @Date: 2020/10/11 18:00
 * @Description:
 * @Version: 1.0
 */
@Service
public class LoginUserServiceImpl extends ServiceImpl<LoginUserMapper, LoginUser> implements ILoginUserService {
    @Autowired
    LoginUserMapper loginUserMapper;

    @Override
    public LoginUser getUserByName(String username) {
        return loginUserMapper.getUserByName(username);
    }
}
