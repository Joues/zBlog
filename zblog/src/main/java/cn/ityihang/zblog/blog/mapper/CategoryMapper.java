package cn.ityihang.zblog.blog.mapper;

import cn.ityihang.zblog.blog.entity.BlogCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Mapper
public interface CategoryMapper extends BaseMapper<BlogCategory> {

}
