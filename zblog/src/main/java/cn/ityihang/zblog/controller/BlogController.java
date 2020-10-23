package cn.ityihang.zblog.controller;

import cn.ityihang.zblog.model.Blog;
import cn.ityihang.zblog.service.IBlogService;
import cn.ityihang.zblog.common.RespPageBean;
import cn.ityihang.zblog.common.RespResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/21 0:28
 */
@Api(tags = "博客表")
@Slf4j
@RestController
@RequestMapping(value = "/blog")
public class BlogController {

    @Autowired
    IBlogService blogService;

    @ApiOperation(value = "全部查询")
    @GetMapping(value = "/all")
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    @ApiOperation(value = "根据id查询{id}")
    @GetMapping(value = "/id")
    public Blog getBlogById(@RequestParam Integer id) {
        return blogService.getBlogById(id);
    }

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/list")
    public RespPageBean getBlogList(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize, Blog blog) {
        return blogService.getBlogList(page, pageSize, blog);
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RespResult deleteBlogById(@PathVariable Integer id) {
        RespResult resp = blogService.deleteBlogById(id);
        return resp;
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RespResult deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        RespResult respResult = blogService.deleteBlogBatchs(Arrays.asList(ids.split(",")));
        return respResult;
    }

    @ApiOperation(value = "添加博客", notes = "添加博客")
    @PostMapping(value = "/add")
    public RespResult addBlog(@RequestBody Blog blog) {
        RespResult respResult = blogService.addBlog(blog);
        return respResult;
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息")
    @PutMapping(value = "/update")
    public RespResult updateBlog(@RequestBody Blog blog) {
        RespResult respResult = blogService.updateBlog(blog);
        return respResult;
    }

}
