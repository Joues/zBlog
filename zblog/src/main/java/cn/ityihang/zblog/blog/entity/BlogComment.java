package cn.ityihang.zblog.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
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
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 父级节点
     */
    private Long pid;

    /**
     * 是否含有子节点
     */
    private Long hasChild;

    /**
     * 博客id（外键）
     */
    private String blogId;

    /**
     * 开始时间
     */
    @Excel(name = "开始时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date createTime;

    /**
     * 结束时间
     */
    @Excel(name = "结束时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private java.util.Date updateTime;

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
    @TableField("e_mail")
    private String eMail;


    /**
     * 修改人
     */
    private String updateBy;

}