package cn.ityihang.zblog.blog.vo;

import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.entity.BlogTag;
import lombok.Data;

import java.util.List;

/**
 * @Author: yihang
 * @Date: 2021/5/16 11:16
 * @Description:
 * @Version: 1.0
 */
@Data
public class BlogPublishVO {
    private Long id;

    private String title;

    private String userId;

    private String summary;

    private String category;

    private List<BlogTag> tags;

    private BlogDetail blogDetail;

}
