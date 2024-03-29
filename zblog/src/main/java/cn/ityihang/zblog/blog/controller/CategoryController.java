package cn.ityihang.zblog.blog.controller;


import cn.ityihang.zblog.blog.entity.BlogCategory;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.blog.service.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Api(tags = "分类表")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    /**
     * 分页列表查询
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogCategory blogCategory, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogCategory> queryWrapper = QueryGenerator.initQueryWrapper(blogCategory, req.getParameterMap());
        Page<BlogCategory> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogCategory> pageList = categoryService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(categoryService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        categoryService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.categoryService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加分类", notes = "添加分类")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody BlogCategory blogCategory) {
        categoryService.save(blogCategory);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改分类信息", notes = "修改分类信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogCategory blogCategory) {
        categoryService.updateById(blogCategory);
        return RestResponse.ok("编辑成功!");
    }
}
