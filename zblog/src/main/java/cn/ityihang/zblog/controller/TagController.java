package cn.ityihang.zblog.controller;


import cn.ityihang.zblog.common.RestResponse;
import cn.ityihang.zblog.entity.Tag;
import cn.ityihang.zblog.service.ITagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2020-11-09
 */
@RestController
@RequestMapping("/zblog/tag")
public class TagController {

    @Autowired
    private ITagService tagService;

    @ApiOperation(value = "最热标签")
    @GetMapping(value = "/hot")
    public RestResponse getBlogHots() {
        LambdaQueryWrapper<Tag> tagLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Tag> page = new Page<>(1,5);
        IPage<Tag> pageList = tagService.page(page, tagLambdaQueryWrapper);
        return RestResponse.ok(pageList);
    }
}
