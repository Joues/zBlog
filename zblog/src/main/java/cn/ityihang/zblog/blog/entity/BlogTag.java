package cn.ityihang.zblog.blog.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-11-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Integer id;
    /**
     * 标签名
     */
    private String name;

    /**
     * 外键（博客id）
     */
    private Integer blogId;

    /**
     * 描述
     */
    private String subscribe;

    /**
     * 图标
     */
    private String avatar;

    /**
     * 创建人
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
