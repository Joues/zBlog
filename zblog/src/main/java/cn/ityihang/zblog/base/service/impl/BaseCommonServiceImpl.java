package cn.ityihang.zblog.base.service.impl;

import cn.ityihang.zblog.base.mapper.BaseCommonMapper;
import cn.ityihang.zblog.base.service.BaseCommonService;
import cn.ityihang.zblog.system.entity.LoginUser;
import cn.ityihang.zblog.system.entity.SysLog;
import cn.ityihang.zblog.utils.IPUtils;
import cn.ityihang.zblog.utils.SpringContextUtils;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import org.apache.shiro.SecurityUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BaseCommonServiceImpl implements BaseCommonService {

    @Resource
    private BaseCommonMapper baseCommonMapper;

    @Override
    public void addLog(SysLog sysLog) {
        if(StringUtils.isEmpty(sysLog.getId())){
            sysLog.setId(String.valueOf(IdWorker.getId()));
        }
        baseCommonMapper.insert(sysLog);
    }

    @Override
    public void addLog(String logContent, Integer logType, Integer operatetype, LoginUser user) {
        SysLog sysLog = new SysLog();
        sysLog.setId(String.valueOf(IdWorker.getId()));
        //注解上的描述,操作日志内容
        sysLog.setLogContent(logContent);
        sysLog.setLogType(logType);
        sysLog.setOperateType(operatetype);
        try {
            //获取request
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            //设置IP地址
            sysLog.setIp(IPUtils.getIpAddr(request));
        } catch (Exception e) {
            sysLog.setIp("127.0.0.1");
        }
        //获取登录用户信息
        if(user==null){
            try {
                user = (LoginUser) SecurityUtils.getSubject().getPrincipal();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        }
        if(user!=null){
            sysLog.setUserid(user.getUsername());
            sysLog.setUsername(user.getRealname());
        }
        sysLog.setCreateTime(new Date());
        //保存系统日志
        baseCommonMapper.saveLog(sysLog);
    }

    @Override
    public void addLog(String logContent, Integer logType, Integer operateType) {
        addLog(logContent, logType, operateType, null);
    }



}
