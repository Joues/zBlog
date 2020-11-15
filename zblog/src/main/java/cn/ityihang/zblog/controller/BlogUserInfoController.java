package cn.ityihang.zblog.controller;

import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.service.IBlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yihang
 * @Date: 2020/11/15 23:07
 * @Description:
 * @Version: 1.0
 */
@Api(value = "博客用户信息")
@RestController
@RequestMapping(value = "/blogInfo")
public class BlogUserInfoController {
    @Autowired
    private IBlogService blogService;

    /**
     * 根据博客id，返回对应的博客详细信息，以及博客对应的创作者信息、分类信息、标签信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/{id}")
    public RestResponse getBlogById(@PathVariable Integer id) {
        return RestResponse.ok(blogService.getById(id), "查询成功");
        //TODO……待实现：根据博客id，返回对应的博客详细信息，以及博客对应的创作者信息、分类信息、标签信息
    }
}
