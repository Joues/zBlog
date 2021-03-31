package cn.ityihang.zblog.blog.controller;


import cn.ityihang.zblog.blog.entity.BlogTag;
import cn.ityihang.zblog.blog.service.ITagService;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
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
 * @since 2020-11-09
 */
@Api(tags = "标签表")
@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    /**
     * 分页列表查询
     * @param blogTag
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogTag blogTag, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogTag> queryWrapper = QueryGenerator.initQueryWrapper(blogTag, req.getParameterMap());
        Page<BlogTag> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogTag> pageList = tagService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(tagService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        tagService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.tagService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加标签", notes = "添加标签")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody BlogTag blogTag) {
        tagService.save(blogTag);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改标签信息", notes = "修改标签信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogTag blogTag) {
        tagService.updateById(blogTag);
        return RestResponse.ok("编辑成功!");
    }
    
    @ApiOperation(value = "最热标签")
    @GetMapping(value = "/hot")
    public RestResponse getBlogHots() {
        LambdaQueryWrapper<BlogTag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<BlogTag> page = new Page<>(1,5);
        IPage<BlogTag> pageList = tagService.page(page, tagLambdaQueryWrapper);
        return RestResponse.ok(pageList);
    }
}
