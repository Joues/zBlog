package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.mapper.SysUserMapper;
import cn.ityihang.zblog.entity.SysUser;
import cn.ityihang.zblog.service.ISysLogService;
import cn.ityihang.zblog.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/9/13 16:39
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    ISysLogService sysLogService;

    @Override
    public SysUser getUserByName(String username) {
        LambdaQueryWrapper<SysUser> sysUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        sysUserLambdaQueryWrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(sysUserLambdaQueryWrapper);
    }

    /**
     * 校验用户是否有效
     * @param sysUser
     * @return
     */
    @Override
    public RestResponse<?> checkUserIsEffective(SysUser sysUser) {
        RestResponse<?> result = new RestResponse<Object>();
        //情况1：根据用户信息查询，该用户不存在
        if (sysUser == null) {
            result.setMsg("该用户不存在，请注册");
            sysLogService.addLog("用户登录失败，用户不存在！", CommonConstant.LOG_TYPE_1, null);
            return result;
        }
        //情况2：根据用户信息查询，该用户已注销
        //update-begin---author:王帅   Date:20200601  for：if条件永远为falsebug------------
        if (CommonConstant.DEL_FLAG_1.equals(sysUser.getDelFlag())) {
            //update-end---author:王帅   Date:20200601  for：if条件永远为falsebug------------
            sysLogService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已注销！", CommonConstant.LOG_TYPE_1, null);
            result.setMsg("该用户已注销");
            return result;
        }
        //情况3：根据用户信息查询，该用户已冻结
        if (CommonConstant.USER_FREEZE.equals(sysUser.getStatus())) {
            sysLogService.addLog("用户登录失败，用户名:" + sysUser.getUsername() + "已冻结！", CommonConstant.LOG_TYPE_1, null);
            result.setMsg("该用户已冻结");
            return result;
        }
        return result;
    }
}
