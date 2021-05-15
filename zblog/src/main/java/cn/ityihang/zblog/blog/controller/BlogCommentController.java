package cn.ityihang.zblog.blog.controller;


import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.service.IBlogService;
import cn.ityihang.zblog.blog.vo.BlogCommentDetailVO;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.blog.entity.BlogComment;
import cn.ityihang.zblog.blog.service.IBlogCommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Api(tags = "评论表")
@RestController
@RequestMapping("/blogComment")
public class BlogCommentController {
    
    @Autowired
    IBlogCommentService blogCommentService;

    @Autowired
    IBlogService blogService;
    
    /**
     * 分页列表查询
     * @param blogComment
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogComment blogComment, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogComment> queryWrapper = QueryGenerator.initQueryWrapper(blogComment, req.getParameterMap());
        Page<BlogComment> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogComment> pageList = blogCommentService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(blogCommentService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        blogCommentService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.blogCommentService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加评论", notes = "添加评论")
    @PostMapping(value = "/add")
    @Transactional(rollbackFor = Exception.class)
    public RestResponse<?> addBlog(@RequestBody BlogComment blogComment) {
//        添加评论时需要重新统计评论的博客总评论数
        Integer blogId = blogComment.getBlogId();
        if (null == blogId) {
            return RestResponse.failed("博客ID(blogId)入参不允许为空！");
        }
        // 根据博客ID（blogId）获取评论表中对应的评论总数
        List<BlogComment> list = blogCommentService.list(new LambdaQueryWrapper<BlogComment>()
                .eq(BlogComment::getBlogId, blogId).select(BlogComment::getId));
        // 修改博客表中的评论数
        blogService.update(new LambdaUpdateWrapper<BlogInfo>().eq(BlogInfo::getId, blogId)
                .set(BlogInfo::getCommentCount, list.size()));
        // 保存评论信息
        blogCommentService.save(blogComment);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改评论信息", notes = "修改评论信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogComment blogComment) {
        blogCommentService.updateById(blogComment);
        return RestResponse.ok("编辑成功!");
    }

    @ApiOperation(value = "根据博客id查询相关评论信息")
    @GetMapping(value = "/bid")
    public RestResponse getCommentByBid(@RequestParam Integer bid) {
        List<BlogCommentDetailVO> commentDetail = blogCommentService.listWithTree(bid);
        return RestResponse.ok(commentDetail, "查询成功");
    }
}
