package cn.ityihang.zblog.base.mapper;

import cn.ityihang.zblog.system.entity.SysLog;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BaseCommonMapper extends BaseMapper<SysLog> {

    /**
     * 保存日志
     * @param sysLog
     */
    @SqlParser(filter=true)
    void saveLog(@Param("dto") SysLog sysLog);

}
