package cn.ityihang.zblog.blog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yihang
 * @Date: 2020/11/16 21:55
 * @Description:
 * @Version: 1.0
 */
@Data
@ApiModel(value = "BlogDetailVO实体", description = "博客详情实体")
public class BlogDetailVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createdTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    /**
     * 作者id（外键）
     */
    private String userId;

    /**
     * 文章概要
     */
    private String summary;

    /**
     * 点赞数（冗余）
     */
    private Long pollCount;

    /**
     * 评论数（冗余）
     */
    private Long commentCount;

    /**
     * 阅读数
     */
    private Long readCount;

    /**
     * 分类ID（外键）
     */
    private Integer classId;

    /**
     * 是否置顶
     */
    private Boolean isTop;

    /**
     * 是否热门
     */
    private Boolean isEssence;


}
