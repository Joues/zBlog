package cn.ityihang.zblog.service;

import cn.ityihang.zblog.entity.Blog;
import cn.ityihang.zblog.entity.BlogComment;
import cn.ityihang.zblog.common.RespPageBean;
import cn.ityihang.zblog.common.RespResult;

import java.util.List;

public interface IBlogCommentService {
    BlogComment getBlogCommentById(Integer id);

    RespPageBean getBlogCommentList(Integer page, Integer pageSize, Blog blog);

    RespResult deleteBlogCommentById(Integer id);

    RespResult deleteBlogBatchs(List<String> asList);

    RespResult addBlogComment(Blog blog);

    RespResult updateBlogComment(Blog blog);
}
