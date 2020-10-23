package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.model.SysUserDetails;

public interface SysUserDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysUserDetails record);

    int insertSelective(SysUserDetails record);

    SysUserDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysUserDetails record);

    int updateByPrimaryKey(SysUserDetails record);
}