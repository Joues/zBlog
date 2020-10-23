package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.model.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogMapper extends BaseMapper<Blog> {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> selectAllBlogs();

    List<Blog> getBlogList(@Param("page") Integer page, @Param("pageSize") Integer pageSize, Blog blog);

    @Select("select count(id) from blog")
    Long getTotal(Blog blog);

    int deleteBlogBatchs(@Param("ids") List<String> asList);
}