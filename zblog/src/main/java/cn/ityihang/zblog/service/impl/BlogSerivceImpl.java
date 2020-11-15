package cn.ityihang.zblog.service.impl;

import cn.ityihang.zblog.mapper.BlogMapper;
import cn.ityihang.zblog.entity.Blog;
import cn.ityihang.zblog.service.IBlogService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: yihangjou(周逸航)
 * @Date: create in 2020/8/21 0:33
 */
@Service
public class BlogSerivceImpl extends ServiceImpl<BlogMapper, Blog> implements IBlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Override
    public List<Map<String, Object>> getBlogNews(Integer sizeNumber) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//        Date date = dateFormat.parse(dateTime);
//        LambdaQueryWrapper<Blog> blogQWrapper = new LambdaQueryWrapper<>();
//        blogQWrapper.select(Blog::getId, Blog::getTitle)
//                .orderByAsc(Blog::getCreatedTime).last("limit " + sizeNumber);
//        List<Blog> blogList = blogMapper.selectList(blogQWrapper);
//        Map<String, Object> blogMaps = blogList.stream().collect(Collectors.toMap(Blog::getId, Blog::getTitle));
//        List<Map<String, Object>> blogs = new ArrayList<>();
//        blogs.add(blogMaps);
        return blogMapper.getBlogNews(sizeNumber);
    }

    /**
     * 最热博客
     *
     * @param sizeNumber
     * @return
     */
    @Override
    public List<Map<String, Object>> getBlogHots(Integer sizeNumber) {
        return blogMapper.getBlogHts(sizeNumber);
    }


}
