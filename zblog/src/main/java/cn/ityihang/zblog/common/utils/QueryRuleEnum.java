package cn.ityihang.zblog.common.utils;

import cn.hutool.core.util.StrUtil;

/**
 * @Author: yihang
 * @Date: 2020/11/13 22:59
 * @Description:
 * @Version: 1.0
 */
public enum QueryRuleEnum {
    GT(">", "gt", "大于"),
    GE(">=", "ge", "大于等于"),
    LT("<", "lt", "小于"),
    LE("<=", "le", "小于等于"),
    EQ("=", "eq", "等于"),
    NE("!=", "ne", "不等于"),
    IN("IN", "in", "包含"),
    LIKE("LIKE", "like", "全模糊"),
    LEFT_LIKE("LEFT_LIKE", "left_like", "左模糊"),
    RIGHT_LIKE("RIGHT_LIKE", "right_like", "右模糊"),
    SQL_RULES("USE_SQL_RULES", "ext", "自定义SQL片段"),
    IN_SQL("inSql", "inSql", "子查询");

    private String value;
    private String condition;
    private String msg;

    private QueryRuleEnum(String value, String condition, String msg) {
        this.value = value;
        this.condition = condition;
        this.msg = msg;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCondition() {
        return this.condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public static QueryRuleEnum getByValue(String value) {
        if (StrUtil.isEmpty(value)) {
            return null;
        } else {
            QueryRuleEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                QueryRuleEnum val = var1[var3];
                if (val.getValue().equals(value) || val.getCondition().equals(value)) {
                    return val;
                }
            }

            return null;
        }
    }
}