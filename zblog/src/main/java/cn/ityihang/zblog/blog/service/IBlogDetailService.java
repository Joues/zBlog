package cn.ityihang.zblog.blog.service;

import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.vo.BlogPublishVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
public interface IBlogDetailService extends IService<BlogDetail> {

    BlogInfo publishBlog(BlogPublishVO publishVO);
}
