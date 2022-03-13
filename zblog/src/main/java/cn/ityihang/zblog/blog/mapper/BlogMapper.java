package cn.ityihang.zblog.blog.mapper;

import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.vo.BlogTagInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
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
@Mapper
public interface BlogMapper extends BaseMapper<BlogInfo> {

    @Select("select id,title from blog_info s\n" +
            "ORDER BY ABS(NOW() - s.create_time) ASC\n" +
            "limit #{sizeNumber};")
    List<Map<String, Object>> getBlogNews(@Param("sizeNumber") Integer sizeNumber);

    @Select("select id,title,read_count,is_top,is_essence from blog_info" +
            " ORDER BY is_essence = 0, read_count desc LIMIT #{sizeNumber}")
    List<Map<String, Object>> getBlogHts(@Param("sizeNumber") Integer sizeNumber);

    /**
     * mybatis-plus多表查询
     * 参数加上@Param(Constants.WRAPPER),xml里加上${ew.customSqlSegment}可以实现复杂条件检索查询
     * @param page
     * @param wrapper
     * @return
     */
    String QUERY_SQL = "select b.*,u.realname from blog_info b LEFT JOIN sys_user u ON b.user_id = u.id";
    /**
     * 高级查询拼接sql
     */
    String WHERE_SQL = " ${ew.customSqlSegment}";

    @Select("<script>" +QUERY_SQL+ WHERE_SQL+"</script>")
    IPage<BlogTagInfoVO> findByPage(Page<BlogInfo> page, @Param(Constants.WRAPPER) QueryWrapper<BlogInfo> wrapper);
}
