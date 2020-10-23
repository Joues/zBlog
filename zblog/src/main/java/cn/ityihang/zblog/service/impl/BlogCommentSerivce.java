package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.mapper.BlogCommentMapper;
import cn.ityihang.zblog.model.Blog;
import cn.ityihang.zblog.model.BlogComment;
import cn.ityihang.zblog.service.IBlogCommentService;
import cn.ityihang.zblog.common.RespPageBean;
import cn.ityihang.zblog.common.RespResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/29 0:36
 */
@Service
public class BlogCommentSerivce implements IBlogCommentService {

    @Autowired
    private BlogCommentMapper blogCommentMapper;

    @Override
    public BlogComment getBlogCommentById(Integer id) {
        return blogCommentMapper.selectByPrimaryKey(id);
    }

    @Override
    public RespPageBean getBlogCommentList(Integer page, Integer pageSize, Blog blog) {
        if (page != null && pageSize != null) {
            page = (page - 1) * pageSize;
        }
        List<Blog> data = blogCommentMapper.getBlogCommentList(page, pageSize, blog);
        Long total = blogCommentMapper.getTotal(blog);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public RespResult deleteBlogCommentById(Integer id) {
        return null;
    }

    @Override
    public RespResult deleteBlogBatchs(List<String> asList) {
        return null;
    }

    @Override
    public RespResult addBlogComment(Blog blog) {
        return null;
    }

    @Override
    public RespResult updateBlogComment(Blog blog) {
        return null;
    }
}
