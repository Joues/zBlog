package cn.ityihang.zblog.common.utils;

import java.io.Serializable;

/**
 * @Author: yihang
 * @Date: 2020/11/15 21:52
 * @Description:
 * @Version: 1.0
 */
public class QueryCondition implements Serializable {
    private static final long serialVersionUID = 4740166316629191651L;
    private String field;
    private String type;
    private String rule;
    private String val;

    public QueryCondition() {
    }

    public String getField() {
        return this.field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getVal() {
        return this.val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        if (this.field != null && !"".equals(this.field)) {
            sb.append(this.field).append(" ").append(this.rule).append(" ").append(this.type).append(" ").append(this.val);
            return sb.toString();
        } else {
            return "";
        }
    }
}