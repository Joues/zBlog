package cn.ityihang.zblog.blog.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class BlogPoll implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 点赞时间
     */
    private LocalDateTime time;

    /**
     * 博客id（外键）
     */
    private Integer blogId;

    /**
     * 发表人
     */
    private String createBy;

    /**
     * 修改人
     */
    private String updateBy;

    /**
     * 创建时间
     */
    private java.sql.Timestamp createTime;

    /**
     * 修改时间
     */
    private java.sql.Timestamp updateTime;


}
