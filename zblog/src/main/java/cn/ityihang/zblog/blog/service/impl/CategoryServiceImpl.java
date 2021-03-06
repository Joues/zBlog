package cn.ityihang.zblog.blog.service.impl;

import cn.ityihang.zblog.blog.entity.BlogCategory;
import cn.ityihang.zblog.blog.mapper.CategoryMapper;
import cn.ityihang.zblog.blog.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, BlogCategory> implements ICategoryService {

}
