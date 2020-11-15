package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.entity.BlogComment;
import cn.ityihang.zblog.mapper.BlogCommentMapper;
import cn.ityihang.zblog.service.IBlogCommentService;
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
