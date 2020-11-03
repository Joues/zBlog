package cn.ityihang.zblog.utils;

import cn.ityihang.zblog.constant.Base;
import cn.ityihang.zblog.entity.SysUser;
import org.apache.shiro.SecurityUtils;


public class UserUtils {

    public static SysUser getCurrentUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(Base.CURRENT_USER);
//        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
