package cn.ityihang.zblog.blog.service.impl;

import cn.ityihang.zblog.blog.entity.BlogTag;
import cn.ityihang.zblog.blog.mapper.TagMapper;
import cn.ityihang.zblog.blog.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-09
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, BlogTag> implements ITagService {

}
