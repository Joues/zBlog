package cn.ityihang.zblog.blog.controller;

import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.entity.BlogCategory;
import cn.ityihang.zblog.blog.entity.BlogTag;
import cn.ityihang.zblog.blog.service.IBlogDetailService;
import cn.ityihang.zblog.blog.service.IBlogService;
import cn.ityihang.zblog.blog.service.ICategoryService;
import cn.ityihang.zblog.blog.service.ITagService;
import cn.ityihang.zblog.common.constant.CommonParam;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.system.entity.SysUser;
import cn.ityihang.zblog.system.entity.SysUserDetails;
import cn.ityihang.zblog.system.service.ISysUserDetailsService;
import cn.ityihang.zblog.system.service.ISysUserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: yihang
 * @Date: 2020/11/15 23:07
 * @Description:
 * @Version: 1.0
 */
@Api(tags = "博客用户信息")
@RestController
@RequestMapping(value = "/blogInfo")
public class BlogUserInfoController {
    @Autowired
    private IBlogService blogService;
    @Autowired
    private IBlogDetailService blogDetailService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISysUserDetailsService sysUserDetailsService;
    @Autowired
    private ICategoryService categoryService;
    @Autowired
    private ITagService tagService;

    /**
     * 根据博客id，返回对应的博客详细信息，以及博客对应的创作者信息、分类信息、标签信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/{id}")
    public RestResponse<?> getBlogById(@PathVariable String id) {
//        获取博客信息
        BlogInfo blog = blogService.getById(id);
        if (null==blog) {
            return RestResponse.failed(CommonParam.ENTITY_ISNULL);
        }
        HashMap<String, Object> blogs = new HashMap<>();
        blogs.put("id", blog.getId());
        blogs.put("userId", blog.getUserId());
        blogs.put("title", blog.getTitle());
        blogs.put("createTime", blog.getCreateTime());
        blogs.put("updateTime", blog.getUpdateTime());
        blogs.put("summary", blog.getSummary());
        blogs.put("pollCount", blog.getUpdateTime());
        blogs.put("commentCount", blog.getCommentCount());
        blogs.put("readCount", blog.getReadCount());
        blogs.put("isTop", blog.getIsTop());
        blogs.put("isEssence", blog.getIsEssence());
        blogs.put("classId", blog.getClassId());

//        获取博客详情信息
        LambdaQueryWrapper<BlogDetail> blogDetailQWrapper = new LambdaQueryWrapper<>();
        blogDetailQWrapper.eq(BlogDetail::getBlogId, blog.getId());
        BlogDetail blogDetail = blogDetailService.getOne(blogDetailQWrapper);
//        获取用户信息
        String userId = blog.getUserId();
        SysUser sysUser = sysUserService.getById(userId);
        if (null==sysUser) {
            return RestResponse.failed(CommonParam.SYSUSER_ISNULL);
        }
//        获取用户详情信息
        LambdaQueryWrapper<SysUserDetails> userDetailQWrapper = new LambdaQueryWrapper<>();
        userDetailQWrapper.eq(SysUserDetails::getUserId, sysUser.getId());
        SysUserDetails userDetails = sysUserDetailsService.getOne(userDetailQWrapper);
//        获取分类信息
        LambdaQueryWrapper<BlogCategory> categoryQWrapper = new LambdaQueryWrapper<>();
        categoryQWrapper.eq(BlogCategory::getId, blog.getClassId());
        BlogCategory blogCategoryInfo = categoryService.getOne(categoryQWrapper);
//        获取标签信息
        List<String> tagId = Arrays.asList(blog.getTagId().split(","));
        LambdaQueryWrapper<BlogTag> tagQWrapper = new LambdaQueryWrapper<>();
        tagQWrapper.in(BlogTag::getId, tagId);
        List<BlogTag> blogTagInfo = tagService.list(tagQWrapper);
//        组装接口返回信息
        HashMap<String, Object> author = new HashMap<>();
        author.put("avatar", userDetails.getAvator());
        author.put("id", String.valueOf(sysUser.getId()));
        author.put("nickname", sysUser.getRealname());

        HashMap<String, Object> body = new HashMap<>();
        body.put("content", blogDetail.getContent());
        body.put("contentHtml", blogDetail.getContentHtml());
        body.put("id", String.valueOf(blogDetail.getBlogId()));

        HashMap<String, Object> category = new HashMap<>();
        category.put("categoryname", blogCategoryInfo.getName());
        category.put("description", blogCategoryInfo.getSubscribe());
        category.put("id", String.valueOf(blogCategoryInfo.getId()));

        List<Object> tags = new ArrayList<>();
        for (BlogTag blogTag : blogTagInfo) {
            HashMap<String, Object> tag = new HashMap<>();
            tag.put("tagname", blogTag.getName());
            tag.put("id", String.valueOf(blogTag.getId()));
            tags.add(tag);
        }

        LinkedHashMap<String, Object> blogInfo = new LinkedHashMap<>();
        blogInfo.put("author", author);
        blogInfo.put("body", body);
        blogInfo.put("category", category);
        blogInfo.put("tags", tags);
        blogInfo.put("blog", blogs);
//        blogInfo.put("id", String.valueOf(id));
        // 记录返回博客的浏览量+1
        Long readCount = blog.getReadCount();
        blog.setReadCount(++readCount);
        blogService.updateById(blog);
        return RestResponse.ok(blogInfo, "查询成功");
        //TODO……待实现：根据博客id，返回对应的博客详细信息，以及博客对应的创作者信息、分类信息、标签信息
    }
}
