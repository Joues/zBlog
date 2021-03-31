package cn.ityihang.zblog.blog.service;

import cn.ityihang.zblog.blog.entity.BlogInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
public interface IBlogService extends IService<BlogInfo> {

    /**
     * 最新博客
     * @param sizeNumber
     * @return
     * @throws ParseException
     */
    List<Map<String, Object>> getBlogNews(Integer sizeNumber) throws ParseException;

    /**
     * 最热博客
     * @param sizeNumber
     * @return
     */
    List<Map<String, Object>> getBlogHots(Integer sizeNumber);
}
