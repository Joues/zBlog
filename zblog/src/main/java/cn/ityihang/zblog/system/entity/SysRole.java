package cn.ityihang.zblog.system.entity;


import java.io.Serializable;

public class SysRole implements Serializable {

  /**
   * 主键ID
   */
  private long id;

  /**
   * 角色编码
   */
  private String roleCode;

  /**
   * 角色名称
   */
  private String role;

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
  private java.sql.Timestamp createTime;

  /**
   * 修改时间
   */
  private java.sql.Timestamp updateTime;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }


  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
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


  public java.sql.Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.sql.Timestamp createTime) {
    this.createTime = createTime;
  }


  public java.sql.Timestamp getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(java.sql.Timestamp updateTime) {
    this.updateTime = updateTime;
  }

}
