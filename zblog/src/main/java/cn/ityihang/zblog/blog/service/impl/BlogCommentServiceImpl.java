package cn.ityihang.zblog.blog.service.impl;

import cn.ityihang.zblog.blog.entity.BlogComment;
import cn.ityihang.zblog.blog.mapper.BlogCommentMapper;
import cn.ityihang.zblog.blog.service.IBlogCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

}
