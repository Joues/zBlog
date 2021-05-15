package cn.ityihang.zblog.blog.service;

import cn.ityihang.zblog.blog.entity.BlogComment;
import cn.ityihang.zblog.blog.vo.BlogCommentDetailVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
public interface IBlogCommentService extends IService<BlogComment> {

    List<BlogCommentDetailVO> listWithTree(Integer bid);
}
