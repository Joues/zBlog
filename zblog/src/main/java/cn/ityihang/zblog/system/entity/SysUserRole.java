package cn.ityihang.zblog.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class SysUserRole implements Serializable {

  /**
   * 主键ID
   */
  private long id;

  /**
   * 系统用户ID
   */
  private long sysUserId;

  /**
   * 系统角色ID
   */
  private long sysRoleId;

  /**
   * 创建人
   */
  private String createBy;

  /**
   * 修改人
   */
  private String updateBy;

  /**
   * 创建时间
   */
  @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  /**
   * 修改时间
   */
  @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private Date updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSysUserId() {
    return sysUserId;
  }

  public void setSysUserId(long sysUserId) {
    this.sysUserId = sysUserId;
  }


  public long getSysRoleId() {
    return sysRoleId;
  }

  public void setSysRoleId(long sysRoleId) {
    this.sysRoleId = sysRoleId;
  }


  public String getCreateBy() {
    return createBy;
  }

  public void setCreateBy(String createBy) {
    this.createBy = createBy;
  }


  public String getUpdateBy() {
    return updateBy;
  }

  public void setUpdateBy(String updateBy) {
    this.updateBy = updateBy;
  }


  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public Date getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
