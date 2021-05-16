package cn.ityihang.zblog.blog.controller;

import cn.ityihang.zblog.blog.vo.BlogCountVO;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.common.param.PageParam;
import cn.ityihang.zblog.common.utils.DateUtils;
import cn.ityihang.zblog.common.utils.QueryGenerator;
import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.ityihang.cn
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/21 0:28
 */
@Api(tags = "博客表")
@Slf4j
@RestController
@RequestMapping(value = "/")
public class BlogController {

    @Autowired
    IBlogService blogService;


    /**
     * 分页列表查询
     * @param blogInfo
     * @param pageParam
     * @param req
     * @return
     */
    @ApiOperation(value = "分页列表查询")
    @GetMapping(value = "/list")
    public RestResponse getBlogList(BlogInfo blogInfo, @Valid PageParam pageParam, HttpServletRequest req) {
        QueryWrapper<BlogInfo> queryWrapper = QueryGenerator.initQueryWrapper(blogInfo, req.getParameterMap());
        queryWrapper.lambda().orderByDesc(BlogInfo::getCreateTime);
        Page<BlogInfo> page = new Page<>(pageParam.getPageNo(), pageParam.getLimit());
        IPage<BlogInfo> pageList = blogService.page(page, queryWrapper);
        return RestResponse.ok(pageList);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "/id")
    public RestResponse getBlogById(@RequestParam Integer id) {
        return RestResponse.ok(blogService.getById(id), "查询成功");
    }

    @ApiOperation(value = "根据id删除")
    @DeleteMapping(value = "/delete/{id}")
    public RestResponse<?> deleteBlogById(@PathVariable Integer id) {
        blogService.removeById(id);
        return RestResponse.ok("删除成功!");
    }

    @ApiOperation(value = "批量删除", notes = "批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public RestResponse<?> deleteBlogBatchs(@RequestParam(name = "ids", required = true) String ids) {
        this.blogService.removeByIds(Arrays.asList(ids.split(",")));
        return RestResponse.ok("批量删除成功！");
    }

    @ApiOperation(value = "添加博客", notes = "添加博客")
    @PostMapping(value = "/add")
    public RestResponse<?> addBlog(@RequestBody BlogInfo blogInfo) {
        blogService.save(blogInfo);
        return RestResponse.ok("添加成功！");
    }

    @ApiOperation(value = "修改博客信息", notes = "修改博客信息")
    @PutMapping(value = "/edit")
    public RestResponse<?> updateBlog(@RequestBody BlogInfo blogInfo) {
        blogService.updateById(blogInfo);
        return RestResponse.ok("编辑成功!");
    }

    @ApiOperation(value = "最新博客")
    @GetMapping(value = "/new")
    public RestResponse getBlogNews(@RequestParam(defaultValue = "5", required = false) Integer sizeNumber) {
        try {
            List<Map<String, Object>> blogNews = blogService.getBlogNews(sizeNumber);
            return RestResponse.ok(blogNews, CommonConstant.TODO_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("异常信息：" + e.getMessage());
            return RestResponse.failed(CommonConstant.TODO_FAILED);
        }
    }

    @ApiOperation(value = "最热博客")
    @GetMapping(value = "/hot")
    public RestResponse getBlogHots(@RequestParam(defaultValue = "5") Integer sizeNumber) {
        try {
            List<Map<String, Object>> blogNews = blogService.getBlogHots(sizeNumber);
            return RestResponse.ok(blogNews, CommonConstant.TODO_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            log.warn("异常信息：" + e.getMessage());
            return RestResponse.failed(CommonConstant.TODO_FAILED);
        }
    }

    /**
     * 博客分类查询，最近12个月博客的统计
     * @return
     */
    @ApiOperation(value = "分类查询")
    @GetMapping(value = "/listArchives")
    public RestResponse getBlogList() {
        // 统计博客系统最近12个月的博客信息
        List<Object> oldYearMonths = DateUtils.getOldYearMonths();
        // 分别获取最早一个年月、及最晚一个年、月
        String endYearMonth = (String) oldYearMonths.get(0);
        String endYear = endYearMonth.substring(0, 4);
        String endMonth = endYearMonth.substring(5, 7);
        // 最晚一个年、月
        String startYearMonth = (String) oldYearMonths.get(oldYearMonths.size()-1);
        String startYear = startYearMonth.substring(0, 4);
        String startMonth = startYearMonth.substring(5, 7);

        List<BlogCountVO> blogInfoList = new ArrayList<>();
        for (Object oldYearMonth : oldYearMonths) {
            // 循环获取每个年月的信息
            String yearMonth = (String) oldYearMonth;
            String year = yearMonth.substring(0, 4);
            String month = yearMonth.substring(5, 7);
            // 组装实体返回数据
            BlogCountVO entity = new BlogCountVO();
            entity.setYear(year);
            entity.setMonth(month);
            // Fixme 博客分类查询，最近12个月博客的统计，对每个月单独统计，待完成
            List<BlogInfo> blogList = blogService.list(new LambdaQueryWrapper<BlogInfo>()
                    .apply("DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( '" + yearMonth + "-01' , '%Y%m' )")
                    .select(BlogInfo::getId));
            entity.setCount(blogList.size());
            blogInfoList.add(entity);
        }
        return RestResponse.ok(blogInfoList);
    }

}
