package cn.ityihang.zblog.common.utils;

import cn.ityihang.zblog.common.constant.CommonParam;
import cn.ityihang.zblog.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;


public class UserUtils {

    public static SysUser getCurrentUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute(CommonParam.CURRENT_USER);
//        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return user;
    }
}
