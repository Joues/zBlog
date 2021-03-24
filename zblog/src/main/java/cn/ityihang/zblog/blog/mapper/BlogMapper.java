package cn.ityihang.zblog.blog.mapper;

import cn.ityihang.zblog.blog.entity.Blog;
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
            "ORDER BY ABS(NOW() - s.create_time) ASC\n" +
            "limit #{sizeNumber};")
    List<Map<String, Object>> getBlogNews(@Param("sizeNumber") Integer sizeNumber);

    @Select("SELECT A.id,A.title from blog A, blog_poll B " +
            "where A.id = B.blog_id GROUP BY B.blog_id " +
            "ORDER BY COUNT(B.id) DESC " +
            "LIMIT #{sizeNumber}")
    List<Map<String, Object>> getBlogHts(@Param("sizeNumber") Integer sizeNumber);
}
