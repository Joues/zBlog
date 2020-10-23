package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.model.Blog;
import cn.ityihang.zblog.model.BlogComment;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BlogCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogComment record);

    int insertSelective(BlogComment record);

    BlogComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogComment record);

    int updateByPrimaryKey(BlogComment record);

    @Select("select count(id) from blog_comment")
    Long getTotal(Blog blog);

    List<Blog> getBlogCommentList(Integer page, Integer pageSize, Blog blog);
}