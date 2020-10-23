package com.ntvu.web2.entity;

import com.ntvu.web2.util.Tools;

/**
 * 数据库 system_users表 对应的java类
 */
public class SystemUsers {
    private int id = 0;
    private String loginName;
    private String loginPassword;
    private String loginSalt = "";
    private String telephone;
    private String email;
    private boolean status = true;
    private int roleId = 2;

    /**
     * 用户对应的角色
     */
    private Roles role;

    public SystemUsers() {}

    public SystemUsers(String loginName, String loginPassword, String loginSalt, String telephone, String email, boolean status, int roleId, Roles role) {
        this.loginName = Tools.isEmpty(loginName, "default");
        this.loginPassword = Tools.isEmpty(loginPassword, "123456");
        this.loginSalt = Tools.isEmpty(loginSalt, "salt");
        this.telephone = Tools.isEmpty(telephone, "1234567890");
        this.email = Tools.isEmpty(email, "123456@123.top");
        this.status = status;
        this.roleId = roleId;
        if (role != null)
            this.role = role;
    }

    public SystemUsers(String loginName, String pwd, String telephone, String email) {
        this(loginName, pwd, null, telephone, email, true, 2, null);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getLoginSalt() {
        return loginSalt;
    }

    public void setLoginSalt(String loginSalt) {
        this.loginSalt = loginSalt;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }
}
