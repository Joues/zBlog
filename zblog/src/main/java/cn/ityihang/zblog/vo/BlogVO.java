package cn.ityihang.zblog.vo;

import cn.ityihang.zblog.entity.Blog;
import lombok.Data;

import java.util.List;

/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/23 13:00
 */
@Data
public class BlogVO {
//    Blog表所有字段
    private List<Blog> records;
//    分页查询长度
    private Long size;
//    分页查询总页数
    private Long pages;
//    分頁查询总数
    private Long Total;
//    分頁查詢當前页
    private Long Current;



}
