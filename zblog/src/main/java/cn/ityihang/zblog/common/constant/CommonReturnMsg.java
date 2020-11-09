package cn.ityihang.zblog.common.constant;


public enum CommonReturnMsg {

    addSuccess(1, "操作成功"),
    delSuccess(2,"删除成功"),
    updateSuccess(3, "修改成功"),

    addFailed(-1, "操作失败"),
    delFailed(-2,"删除失败"),
    updateFailed(-3, "修改失败");


    private Integer code;
    private String name;

    CommonReturnMsg(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

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
}
