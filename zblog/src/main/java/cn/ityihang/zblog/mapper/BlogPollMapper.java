package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.entity.BlogPoll;

public interface BlogPollMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogPoll record);

    int insertSelective(BlogPoll record);

    BlogPoll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogPoll record);

    int updateByPrimaryKey(BlogPoll record);
}