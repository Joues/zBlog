package cn.ityihang.zblog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 博客id（外键）
     */
    private Integer blogId;

    /**
     * 评论时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date createTime;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论人
     */
    private String createBy;

    /**
     * 电子邮箱
     */
    @TableField("eMail")
    private String eMail;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Shanghai")
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateBy;

}