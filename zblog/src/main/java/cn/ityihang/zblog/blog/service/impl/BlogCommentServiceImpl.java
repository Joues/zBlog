package cn.ityihang.zblog.blog.service.impl;

import cn.ityihang.zblog.blog.entity.BlogComment;
import cn.ityihang.zblog.blog.mapper.BlogCommentMapper;
import cn.ityihang.zblog.blog.service.IBlogCommentService;
import cn.ityihang.zblog.blog.vo.BlogAuthorVO;
import cn.ityihang.zblog.blog.vo.BlogCommentDetailVO;
import cn.ityihang.zblog.common.constant.CommonConstant;
import cn.ityihang.zblog.common.result.RestResponse;
import cn.ityihang.zblog.system.entity.SysUser;
import cn.ityihang.zblog.system.mapper.SysUserMapper;
import cn.ityihang.zblog.utils.oConvertUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements IBlogCommentService {

    @Autowired
    BlogCommentMapper blogCommentMapper;

    @Autowired
    SysUserMapper userMapper;

    @Override
    public List<BlogCommentDetailVO> listWithTree(String bid) {
        List<BlogComment> commentList = blogCommentMapper.selectList(
                new LambdaQueryWrapper<BlogComment>().eq(BlogComment::getBlogId, bid));
        List<BlogCommentDetailVO> commentDetail = new ArrayList<>();
        if (commentList!=null && commentList.size()>0) {
            commentList = commentList.stream().sorted(Comparator.comparing(BlogComment::getCreateTime).reversed()).collect(Collectors.toList());
            commentDetail = commentDetailVOList(commentList);
        }
        return commentDetail;
    }

    /**
     * 需要返回的所有父级评论
     * @param commentList
     * @return
     */
    public List<BlogCommentDetailVO> commentDetailVOList(List<BlogComment> commentList) {
        List<BlogComment> commentLists = new ArrayList<>();
        commentList.forEach( c -> {
            if (null == c.getPid()) {
                // 1. 组装返回信息
                commentLists.add(c);
            }
        });
        return buildToTree(commentLists);
    }

    /**
     * 组装树形数据结构
     * @param commentList
     * @return
     */
    private  List<BlogCommentDetailVO> buildToTree(List<BlogComment> commentList) {
        List<BlogCommentDetailVO> treeResult = new ArrayList<>();
        commentList.forEach(p -> {
            // 1. 组装返回信息
            BlogCommentDetailVO entity = new BlogCommentDetailVO();
            BeanUtils.copyProperties(p, entity);
            // 2. 根据每条评论的创建人获取对应的用户信息
            SysUser sysUser = userMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                    .eq(SysUser::getUsername, entity.getCreateBy())
                    .select(SysUser::getId, SysUser::getAvatar, SysUser::getRealname));
            BlogAuthorVO authorVO = new BlogAuthorVO();
            authorVO.setId(Long.valueOf(sysUser.getId()));
            authorVO.setAvatar(sysUser.getAvatar());
            authorVO.setNickname(sysUser.getRealname());
            entity.setAuthor(authorVO);
            if (CommonConstant.MENU_TYPE_1.equals(Math.toIntExact(entity.getHasChild()))) {
                entity.setChildrens(buildToTree(blogCommentMapper.selectList(
                        new LambdaQueryWrapper<BlogComment>()
                                .eq(BlogComment::getPid, entity.getId()))));
            } else {
                entity.setChildrens(new ArrayList<>());
            }
            treeResult.add(entity);
        });
        return treeResult;
    }
}
