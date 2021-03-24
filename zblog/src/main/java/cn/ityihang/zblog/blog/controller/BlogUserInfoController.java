package cn.ityihang.zblog.blog.controller;

import cn.ityihang.zblog.blog.entity.Blog;
import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.entity.Category;
import cn.ityihang.zblog.blog.entity.Tag;
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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;

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
    public RestResponse<?> getBlogById(@PathVariable Integer id) {
//        获取博客信息
        Blog blog = blogService.getById(id);
        if (null==blog) {
            return RestResponse.failed(CommonParam.ENTITY_ISNULL);
        }
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
        LambdaQueryWrapper<Category> categoryQWrapper = new LambdaQueryWrapper<>();
        categoryQWrapper.eq(Category::getIdBlog, id);
        Category categoryInfo = categoryService.getOne(categoryQWrapper);
//        获取标签信息
        LambdaQueryWrapper<Tag> tagQWrapper = new LambdaQueryWrapper<>();
        tagQWrapper.eq(Tag::getBlogId, id);
        Tag tagInfo = tagService.getOne(tagQWrapper);
//        组装接口返回信息
        HashMap<String, String> author = new HashMap<>();
        author.put("avatar", userDetails.getAvator());
        author.put("id", String.valueOf(sysUser.getId()));
        author.put("nickname", userDetails.getNickname());

        HashMap<String, String> body = new HashMap<>();
        body.put("content", blogDetail.getContent());
        body.put("id", String.valueOf(blogDetail.getBlogId()));

        HashMap<String, String> category = new HashMap<>();
        category.put("categoryname", categoryInfo.getName());
        category.put("description", categoryInfo.getSubscribe());
        category.put("id", String.valueOf(categoryInfo.getId()));

        HashMap<String, String> tags = new HashMap<>();
        tags.put("tagname", tagInfo.getName());
        tags.put("id", String.valueOf(tagInfo.getId()));

        LinkedHashMap<String, HashMap<String, String>> blogInfo = new LinkedHashMap<>();
        blogInfo.put("author", author);
        blogInfo.put("body", body);
        blogInfo.put("category", category);
        blogInfo.put("tags", tags);
//        blogInfo.put("id", String.valueOf(id));
        return RestResponse.ok(blogInfo, "查询成功");
        //TODO……待实现：根据博客id，返回对应的博客详细信息，以及博客对应的创作者信息、分类信息、标签信息
    }
}
