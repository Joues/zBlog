package cn.ityihang.zblog.controller;


import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.entity.BlogPoll;
import cn.ityihang.zblog.service.IBlogPollService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.repository.support.QueryByExampleRedisExecutor;
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
@Api(tags = "点赞表")
@RestController
@RequestMapping("/blogPoll")
public class BlogPollController {

    @Autowired
    IBlogPollService blogPollService;

    /**
     * 分页列表查询
     * @param blog
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogPoll blogPoll, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogPoll> queryWrapper = QueryGenerator.initQueryWrapper(blogPoll, req.getParameterMap());
        Page<BlogPoll> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogPoll> pageList = blogPollService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(blogPollService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        blogPollService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.blogPollService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加点赞", notes = "添加点赞")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody BlogPoll blogPoll) {
        blogPollService.save(blogPoll);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改点赞信息", notes = "修改点赞信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogPoll blogPoll) {
        blogPollService.updateById(blogPoll);
        return RestResponse.ok("编辑成功!");
    }
}
