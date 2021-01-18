package cn.ityihang.zblog.blog.controller;

import cn.ityihang.zblog.aspect.annotation.AutoLog;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.blog.entity.Blog;
import cn.ityihang.zblog.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/21 0:28
 */
@Api(tags = "博客表")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class BlogController {

    @Autowired
    IBlogService blogService;


    /**
     * 分页列表查询
     * @param blog
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(Blog blog, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<Blog> queryWrapper = QueryGenerator.initQueryWrapper(blog, req.getParameterMap());
        Page<Blog> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<Blog> pageList = blogService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(blogService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        blogService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.blogService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加博客", notes = "添加博客")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody Blog blog) {
        blogService.save(blog);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody Blog blog) {
        blogService.updateById(blog);
        return RestResponse.ok("编辑成功!");
    }

    @ApiOperation(value = "最新博客")
    @GetMapping(value = "/new")
    public RestResponse getBlogNews(@RequestParam(defaultValue = "5") Integer sizeNumber) {
        try {
            List<Map<String, Object>> blogNews = blogService.getBlogNews(sizeNumber);
            return RestResponse.ok(blogNews, CommonConstant.TODO_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("异常信息：" + e.getMessage());
            return RestResponse.failed(CommonConstant.TODO_FAILED);
        }
    }

    @ApiOperation(value = "最热博客")
    @GetMapping(value = "/hot")
    public RestResponse getBlogHots(@RequestParam(defaultValue = "5") Integer sizeNumber) {
        try {
            List<Map<String, Object>> blogNews = blogService.getBlogHots(sizeNumber);
            return RestResponse.ok(blogNews, CommonConstant.TODO_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("异常信息：" + e.getMessage());
            return RestResponse.failed(CommonConstant.TODO_FAILED);
        }
    }

}
