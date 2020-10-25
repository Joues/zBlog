package cn.ityihang.zblog.service;

import cn.ityihang.zblog.entity.Blog;
import cn.ityihang.zblog.common.RespPageBean;
import cn.ityihang.zblog.common.RespResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IBlogService extends IService<Blog> {

    List<Blog> getAllBlogs();

    Blog getBlogById(Integer id);

    RespResult deleteBlogById(Integer id);

    RespPageBean getBlogList(Integer page, Integer pageSize, Blog blog);

    RespResult addBlog(Blog blog);

    RespResult updateBlog(Blog blog);

    RespResult deleteBlogBatchs(List<String> asList);

}
