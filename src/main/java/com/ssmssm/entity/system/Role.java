package com.ssmssm.entity.system;

import java.io.Serializable;

public class Role extends RoleKey implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_auth_role.role_name
     * @mbggenerated
     */
    private String roleName;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_auth_role.role_enabled
     * @mbggenerated
     */
    private Short roleEnabled;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_auth_role.comments
     * @mbggenerated
     */
    private String comments;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_auth_role
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_auth_role.role_name
     * @return  the value of t_auth_role.role_name
     * @mbggenerated
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_auth_role.role_name
     * @param roleName  the value for t_auth_role.role_name
     * @mbggenerated
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_auth_role.role_enabled
     * @return  the value of t_auth_role.role_enabled
     * @mbggenerated
     */
    public Short getRoleEnabled() {
        return roleEnabled;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_auth_role.role_enabled
     * @param roleEnabled  the value for t_auth_role.role_enabled
     * @mbggenerated
     */
    public void setRoleEnabled(Short roleEnabled) {
        this.roleEnabled = roleEnabled;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_auth_role.comments
     * @return  the value of t_auth_role.comments
     * @mbggenerated
     */
    public String getComments() {
        return comments;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_auth_role.comments
     * @param comments  the value for t_auth_role.comments
     * @mbggenerated
     */
    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_auth_role
     * @mbggenerated
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleName=").append(roleName);
        sb.append(", roleEnabled=").append(roleEnabled);
        sb.append(", comments=").append(comments);
        sb.append("]");
        return sb.toString();
    }
}