package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.entity.Category;
import cn.ityihang.zblog.mapper.CategoryMapper;
import cn.ityihang.zblog.service.ICategoryService;
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
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
