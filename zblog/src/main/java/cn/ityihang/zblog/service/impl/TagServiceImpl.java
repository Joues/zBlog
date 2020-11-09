package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.entity.Tag;
import cn.ityihang.zblog.mapper.TagMapper;
import cn.ityihang.zblog.service.ITagService;
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
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

}
