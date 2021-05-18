package cn.ityihang.zblog.blog.controller;


import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.vo.BlogPublishVO;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.service.IBlogDetailService;
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
@Api(tags = "博客详情表")
@RestController
@RequestMapping("/blogDetail")
public class BlogDetailController {

    @Autowired
    IBlogDetailService blogDetailService;

    /**
     * 分页列表查询
     * @param blogDetail
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogDetail blogDetail, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogDetail> queryWrapper = QueryGenerator.initQueryWrapper(blogDetail, req.getParameterMap());
        Page<BlogDetail> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogDetail> pageList = blogDetailService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id/{id}")
    public RestResponse getBlogById(@PathVariable String id) {
        return RestResponse.ok(blogDetailService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        blogDetailService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.blogDetailService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加博客详情", notes = "添加博客详情")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody BlogDetail blogDetail) {
        blogDetailService.save(blogDetail);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改博客详情信息", notes = "修改博客详情信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogDetail blogDetail) {
        blogDetailService.updateById(blogDetail);
        return RestResponse.ok("编辑成功!");
    }

    @ApiOperation(value = "发布博客信息", notes = "发布博客信息")
    @PostMapping(value = "/publish")
    public RestResponse<?> publishBlog(@RequestBody BlogPublishVO publishVO) {
        BlogInfo blogInfo = blogDetailService.publishBlog(publishVO);
        return RestResponse.ok(blogInfo, "发布成功！");
    }
}
