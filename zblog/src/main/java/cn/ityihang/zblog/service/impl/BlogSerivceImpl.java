package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.common.RestResponse;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.mapper.BlogMapper;
import cn.ityihang.zblog.entity.Blog;
import cn.ityihang.zblog.service.IBlogService;
import cn.ityihang.zblog.common.RespResult;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: yihangjou(周逸航)
 * @Date: create in 2020/8/21 0:33
 */
@Service
public class BlogSerivceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Blog> getAllBlogs() {
        return blogMapper.selectAllBlogs();
    }

    @Override
    public Blog getBlogById(Integer id) {
        return blogMapper.selectByPrimaryKey(id);
    }

    @Override
    public RespResult addBlog(Blog blog) {
        int insert = blogMapper.insert(blog);
        if (insert == 1) {
            return RespResult.ok(CommonConstant.TODO_SUCCESS);
        }
        return RespResult.error(CommonConstant.TODO_FAILED);
    }

    @Override
    public RespResult updateBlog(Blog blog) {
        int insert = blogMapper.updateByPrimaryKeySelective(blog);
        if (insert == 1) {
            return RespResult.ok(CommonConstant.TODO_SUCCESS);
        }
        return RespResult.error(CommonConstant.TODO_FAILED);
    }

    @Override
    public RespResult deleteBlogBatchs(List<String> asList) {
        if (blogMapper.deleteBlogBatchs(asList) == 1) {
            return RespResult.ok(CommonConstant.TODO_SUCCESS);
        }
        return RespResult.error(CommonConstant.TODO_FAILED);
    }

    @Override
    public List<Map<String, Object>> getBlogNews(Integer sizeNumber) {
        return blogMapper.getBlogNews(sizeNumber);
    }

    @Override
    public RespResult deleteBlogById(Integer id) {
        int i = blogMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return RespResult.ok(CommonConstant.TODO_SUCCESS);
        }
        return RespResult.error(CommonConstant.TODO_FAILED);
    }

    @Override
    public RestResponse getBlogList(Integer page, Integer pageSize, Blog blog) {
        if (page != null && pageSize != null) {
            page = (page - 1) * pageSize;
        }
        List<Blog> data = blogMapper.getBlogList(page, pageSize, blog);
        Long total = blogMapper.getTotal(blog);
        RestResponse bean = new RestResponse();
        bean.setData(data);
//        bean.set(total);
        return bean;
    }


}
