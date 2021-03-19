package cn.ityihang.zblog.blog.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "BlogCountVO实体", description = "博客信息统计")
public class BlogCountVO {

    /**
     * 评论总数
     */
    private Integer commentCounts;

    /**
     * 总博客数
     */
    private Integer count;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 简述
     */
    private String summary;

    /**
     * 博客标题
     */
    private String title;

    /**
     * 浏览数
     */
    private String viewCounts;

    /**
     *
     */
    private String weight;

    /**
     * 年份
     */
    private Integer year;
}
