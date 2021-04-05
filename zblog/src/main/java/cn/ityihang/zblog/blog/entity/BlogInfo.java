package cn.ityihang.zblog.blog.entity;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogInfo implements Serializable {

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
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
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

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

}
