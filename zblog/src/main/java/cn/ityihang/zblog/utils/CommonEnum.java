package cn.ityihang.zblog.utils;


/**
 * @Author: yihangjou(周逸航)
 * @Site: www.yihang.ml
 * @cnBlogs: https://www.cnblogs.com/yihangjou/
 * @Date: create in 2020/8/13 23:07
 */
public enum  CommonEnum {
    Monday(1,"周一"),
    TuesDay(2,"周二"),
    WensDay(3,"周三");

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    CommonEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
}


