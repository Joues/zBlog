package cn.ityihang.zblog.service;

import cn.ityihang.zblog.common.RestResponse;
import cn.ityihang.zblog.model.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;

public interface ISysUserService extends IService<SysUser> {
    SysUser getUserByName(String username);

    RestResponse checkUserIsEffective(SysUser sysUser);
}
