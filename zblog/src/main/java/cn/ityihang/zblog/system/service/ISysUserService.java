package cn.ityihang.zblog.system.service;

import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISysUserService extends IService<SysUser> {
    SysUser getUserByName(String username);

    RestResponse checkUserIsEffective(SysUser sysUser);
}
