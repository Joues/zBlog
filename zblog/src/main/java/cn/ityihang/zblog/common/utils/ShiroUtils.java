package cn.ityihang.zblog.common.utils;

import cn.ityihang.zblog.system.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;




/**
 * @Author: yihang
 * @Date: 2021/5/21 18:40
 * @Description:
 * @Version: 1.0
 */
public class ShiroUtils {

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    /**获取shiro的连接器*/
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**获取登录用户的信息*/
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();
    }

    /**获取登录用户的id*/
    public static Integer getUserId() {
        return getUser().getId();
    }

    /**获取登录用户的username*/
    public static String getName() {
        return getUser().getUsername();
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    /**用于判断有没有获取登录用户的信息*/
    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }
    /**注销用户*/
    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static String getKaptcha(String key) {
        String kaptcha = getSessionAttribute(key).toString();
        getSession().removeAttribute(key);
        return kaptcha;
    }

}
