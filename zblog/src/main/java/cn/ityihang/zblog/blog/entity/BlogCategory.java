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
 * @since 2020-11-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 分类名
     */
    private String name;

    /**
     * 父分类id
     */
    private Integer pid;

    /**
     * 描述
     */
    private String subscribe;

    /**
     * 博客表外键
     */
    private Integer idBlog;


}
