package cn.ityihang.zblog.blog.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.ityihang.zblog.blog.entity.BlogDetail;
import cn.ityihang.zblog.blog.entity.BlogInfo;
import cn.ityihang.zblog.blog.entity.BlogTag;
import cn.ityihang.zblog.blog.mapper.BlogDetailMapper;
import cn.ityihang.zblog.blog.mapper.BlogMapper;
import cn.ityihang.zblog.blog.service.IBlogDetailService;
import cn.ityihang.zblog.blog.vo.BlogPublishVO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jobob
 * @since 2020-11-11
 */
@Service
public class BlogDetailServiceImpl extends ServiceImpl<BlogDetailMapper, BlogDetail> implements IBlogDetailService {

    @Autowired
    BlogDetailMapper blogDetailMapper;

    @Autowired
    BlogMapper blogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogInfo publishBlog(BlogPublishVO publishVO) {
        // 先查询是否存在该博客，存在即为编辑，不存在就保存
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setTitle(publishVO.getTitle());
        blogInfo.setSummary(publishVO.getSummary());
        blogInfo.setUserId(publishVO.getUserId());
        blogInfo.setClassId(publishVO.getCategory());
        List<BlogTag> tags = publishVO.getTags();
        if (tags != null && tags.size()>0) {
            Set<Integer> tagIds = tags.stream().map(BlogTag::getId).collect(Collectors.toSet());
            String tagId = StrUtil.join(",", tagIds);
            blogInfo.setTagId(tagId);
        }
        BlogInfo blog = blogMapper.selectById(publishVO.getId());
        if (null != blog) {
            blogMapper.updateById(blogInfo);
            blogDetailMapper.updateById(publishVO.getBlogDetail());
        } else {
            // 1. 保存博客基础信息
            blogMapper.insert(blogInfo);
            // 2. 保存博客详细信息
            BlogDetail blogDetail = publishVO.getBlogDetail();
            blogDetail.setBlogId(blogInfo.getId());
            blogDetailMapper.insert(blogDetail);
        }

        return blogInfo;
    }
}
