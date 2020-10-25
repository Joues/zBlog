package cn.ityihang.zblog.service;

import cn.ityihang.zblog.entity.SysLog;
import cn.ityihang.zblog.entity.LoginUser;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Author: yihang
 * @Date: 2020/10/11 18:32
 * @Description: 日志类业务层接口
 * @Version: 1.0
 */
public interface ISysLogService extends IService<SysLog> {
    /**
     * 保存日志
     * @param logDTO
     */
    void addLog(SysLog logDTO);

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
