package cn.ityihang.zblog.system.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志表(SysLog)实体类
 *
 * @author makejava
 * @since 2020-10-11 18:31:18
 */
public class SysLog implements Serializable {
    private static final long serialVersionUID = 751320797540200472L;

    private String id;
    /**
     * 日志类型（1登录日志，2操作日志）
     */
    private Integer logType;
    /**
     * 日志内容
     */
    private String logContent;
    /**
     * 操作类型
     */
    private Integer operateType;
    /**
     * 操作用户账号
     */
    private String userid;
    /**
     * 操作用户名称
     */
    private String username;
    /**
     * IP
     */
    private String ip;
    /**
     * 请求java方法
     */
    private String method;
    /**
     * 请求路径
     */
    private String requestUrl;
    /**
     * 请求参数
     */
    private Object requestParam;
    /**
     * 请求类型
     */
    private String requestType;
    /**
     * 耗时
     */
    private Long costTime;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLogType() {
        return logType;
    }

    public void setLogType(Integer logType) {
        this.logType = logType;
    }

    public String getLogContent() {
        return logContent;
    }

    public void setLogContent(String logContent) {
        this.logContent = logContent;
    }

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public Object getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Object requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}