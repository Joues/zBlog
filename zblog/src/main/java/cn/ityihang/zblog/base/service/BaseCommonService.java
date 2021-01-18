package cn.ityihang.zblog.base.service;


import cn.ityihang.zblog.system.entity.LoginUser;
import cn.ityihang.zblog.system.entity.SysLog;

/**
 * common接口
 */
public interface BaseCommonService {

    /**
     * 保存日志
     * @param sysLog
     */
    void addLog(SysLog sysLog);

    /**
     * 保存日志
     * @param LogContent
     * @param logType
     * @param operateType
     * @param user
     */
    void addLog(String LogContent, Integer logType, Integer operateType, LoginUser user);

    /**
     * 保存日志
     * @param LogContent
     * @param logType
     * @param operateType
     */
    void addLog(String LogContent, Integer logType, Integer operateType);

}
