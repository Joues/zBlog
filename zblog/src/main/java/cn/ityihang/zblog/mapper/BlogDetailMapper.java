package cn.ityihang.zblog.mapper;

import cn.ityihang.zblog.model.BlogDetail;

public interface BlogDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BlogDetail record);

    int insertSelective(BlogDetail record);

    BlogDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BlogDetail record);

    int updateByPrimaryKeyWithBLOBs(BlogDetail record);

    int updateByPrimaryKey(BlogDetail record);
}