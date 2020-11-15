package cn.ityihang.zblog.controller;


import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.entity.Category;
import cn.ityihang.zblog.service.ICategoryService;
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
    public RestResponse getBlogList(Category category, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<Category> queryWrapper = QueryGenerator.initQueryWrapper(category, req.getParameterMap());
        Page<Category> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<Category> pageList = categoryService.page(page, queryWrapper);
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

    @ApiOperation(value = "添加博客", notes = "添加博客")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody Category category) {
        categoryService.save(category);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody Category category) {
        categoryService.updateById(category);
        return RestResponse.ok("编辑成功!");
    }
}
