package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
public interface BlogMapper extends BaseMapper<Blog> {

    @Select("select id,title from blog s\n" +
            "ORDER BY ABS(NOW() - s.created_time) ASC\n" +
            "limit #{sizeNumber};")
    List<Map<String, Object>> getBlogNews(@Param("sizeNumber") Integer sizeNumber);
}
