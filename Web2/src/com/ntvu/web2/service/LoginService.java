package com.ntvu.web2.service;

import com.ntvu.web2.entity.Roles;
import com.ntvu.web2.entity.SystemUsers;
import com.ntvu.web2.util.Tools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现数据库后台逻辑代码
 */
public class LoginService {

    private final String url;
    private final String dbUserName;
    private final String dbPwd;

    List<SystemUsers> users = null;
    ResultSet rs = null;
    Connection conn = null;
    Statement stmt = null;

    /**
     * 数据库连接信息
     */
    public LoginService() {
//        url = "jdbc:mysql://localhost:3306/web2?serverTimezone=Asia/Shanghai";
        url = "jdbc:mysql://localhost:3306/study_jsp?serverTimezone=Asia/Shanghai";
        dbUserName = "root";
//        dbPwd = "123456";
        dbPwd = "root@123";
    }

    public LoginService(String url, String dbUserName, String dbPwd) {
        this.url = url;
        this.dbUserName = dbUserName;
        this.dbPwd = dbPwd;
    }

    /**
     * 实现用户登录功能
     * @param loginName 用户名
     * @param password 用户密码
     * @return Boolean 成功返回 true，失败返回 false
     */
    public boolean login(String loginName, String password) {
        String sql = String.format("select * from system_users where login_name='%s' and login_password='%s'", loginName, Tools.md5(password));
        return getSystemUsers(sql) != null && !getSystemUsers(sql).isEmpty();
    }

    /**
     * 实现用户登录功能
     * @param user 用户类 SystemUsers
     * @return Boolean 成功返回 true，失败返回 false
     */
    public boolean login(SystemUsers user) {
        String sql = String.format("select id, login_name from system_users where login_name='%s' and login_password='%s'", user.getLoginName(), Tools.md5(user.getLoginPassword()));
        return getSystemUsers(sql) != null && getSystemUsers(sql).isEmpty();
    }

    /**
     * 实现用户注册功能
     * @param loginName 用户名
     * @param loginPassword 用户密码
     * @param loginSalt 盐
     * @param telephone 手机号码
     * @param email 邮箱
     * @param status 状态
     * @param role_id 用户身份
     * @return row(s) 受影响的行数
     */
    public boolean register(String loginName, String loginPassword, String loginSalt, String telephone, String email, boolean status, int role_id) {
        String sql = String.format("INSERT INTO system_users(login_name, login_password, login_salt, telephone, email, status, role_id) VALUES ('%s', '%s', '%s', '%s', '%s', %b, %d)",
                loginName, Tools.md5(loginPassword), loginSalt, telephone, email, status, role_id);
        return updateSql(sql) > 0;
    }

    /**
     * 实现用户注册功能
     * @param user 用户类 SystemUsers
     * @return row(s) 受影响的行数
     */
    public boolean register(SystemUsers user) {
        String sql = String.format("INSERT INTO system_users(login_name, login_password, login_salt, telephone, email, status, role_id) VALUES ('%s', '%s', '%s', '%s', '%s', %b, %d)",
                user.getLoginName(), Tools.md5(user.getLoginPassword()), user.getLoginSalt(), user.getTelephone(), user.getEmail(), user.isStatus(), user.getRoleId());

        return updateSql(sql) > 0;
    }

    /**
     * 通过 id 号删除对应用户
     * @param id id
     * @return row(s) 受影响的行数
     */
    public boolean delete(int id) {
        String sql = String.format("delete from system_users where id = %d", id);
        return updateSql(sql) > 0;
    }

    /**
     * 通过一组 id 号删除对应用户
     * @param id 多个id
     * @return row(s) 受影响的行数
     */
    public boolean delete(int...id) {
        StringBuilder ids = new StringBuilder();
        for (int tmp : id) {
            ids.append(tmp).append(",");
        }
        if (ids.length() > 0)
            ids.substring(0, ids.length() - 1);     // 去除末尾的逗号
        String sql = String.format("delete from system_users where id in (%s)", ids.toString());
        return updateSql(sql) > 0;
    }

    /**
     * 通过用户名删除对应用户
     * @param loginName 用户名
     * @return row(s) 受影响的行数
     */
    public boolean delete(String loginName) {
        String sql = String.format("delete from system_users where login_name = '%s';", loginName);
        return updateSql(sql) > 0;
    }

    /**
     * 通过一组用户名删除对应用户
     * @param loginName 多个用户名
     * @return row(s) 受影响的行数
     */
    public boolean delete(String...loginName) {
        // 此处 IDEA 智能转换为 StringBuilder 类 使用 append() 代替了 +=
        StringBuilder loginNames = new StringBuilder();
        for (String name : loginName) {
            loginNames.append("'").append(name).append("',");
        }
        if (loginNames.length() > 0)
            loginNames.substring(0, loginNames.length() - 1);     // 去除末尾的逗号
        String sql = String.format("delete from system_users where login_name in (%s)", loginNames.toString());
        return updateSql(sql) > 0;
    }

    /**
     * 通过 roleId 删除对应用户组的数据
     * @param roleId 用户身份
     * @return Boolean 成功返回 true 失败返回 false
     */
    public boolean deleteByRoleId(int roleId) {
        String sql = "delete form system_users where role_id = " + roleId;
        return updateSql(sql) > 0;
    }

    /**
     * 通过用户名获取对应的对象
     * @param loginName 用户名
     * @return SystemUsers 对象
     */
    public SystemUsers findByLoginName(String loginName) {
        String sql = String.format("select login_name, telephone, email, role_id from system_users where login_name = '%s'", loginName);
        users = getSystemUsers(sql);
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * 通过 用户id 获取对应的对象
     * @param id id
     * @return SystemUsers
     */
    public SystemUsers findById(int id) {
        String sql = String.format("select login_name, telephone, email, role_id from system_users where login_name = %d", id);
        users = getSystemUsers(sql);
        return users.isEmpty() ? null : users.get(0);
    }

    /**
     * 返回当前所有用户列表
     * @return List<SystemUsers>
     */
    public List<SystemUsers> getList() {
        String sql = "select * from system_users";
        return getSystemUsers(sql);
    }

    /**
     * 通过 用户 id 号 查找对应用户详细信息
     * @param id id
     * @return List<SystemUsers>
     */
    public SystemUsers getDetails(int id) {
        return findById(id);
    }

    /**
     * 通过 用户id 修改用户信息
     * @param user 用户 (SystemUsers) 对象
     * @return 成功返回 true 失败返回 false
     */
    public boolean edit(SystemUsers user) {
        if(user == null || user.getLoginName() == null || user.getTelephone() == null || user.getEmail() == null)
            return false;

        String sql  = "update system_users set login_name=%s,telephone=%s,email=%s,status=%b,role_id=%d where id=%d";
        sql = String.format(sql,user.getLoginName(),user.getTelephone(),user.getEmail(),user.isStatus(),user.getRoleId(),user.getId());
        return updateSql(sql) > 0;
    }

    /**
     * 通过 用户名 查询该用户状态是否可用
     * @param loginName 用户名
     * @return 可用为 true， 不可以为 false
     */
    public boolean valid(String loginName) {
        return findByLoginName(loginName).isStatus();
    }

    /**
     * 通过 用户id 查询该用户状态是否可用
     * @param id 用户 id
     * @return 可用为 true， 不可以为 false
     */
    public boolean valid(int id) {
        return findById(id).isStatus();
    }

    /**
     * 修改对应用户的密码
     * @param user 用户对象
     * @param pwd 新密码
     * @return 设置新密码
     */
    public boolean resetPassword(SystemUsers user, String pwd) {
        String sql = String.format("update system_users set login_password = '%s' where login_name = '%s'", user.getLoginName(), pwd);
        return updateSql(sql) > 0;
    }

    /**
     * 生成连接和句柄
     */
    private void createConnection() {
        try {
            // 1.注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2.创建于数据库的连接
            conn = DriverManager.getConnection(url, dbUserName, dbPwd);
            // 3.获取执行句柄
            stmt = conn.createStatement();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * 释放连接和句柄
     */
    private void closeConnection() {
        try {
            if (conn != null)
                conn.close();
            //TODO delete
            stmt.close();
            if (stmt != null)
                stmt.close();
            if (rs != null)
                rs.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    /**
     * 执行 INSERT UPDATE DELETE 及 CREATE DROP Sql语句, 返回受影响的行数
     * @param sql 语句
     * @return row(s) 受影响的行数
     */
    private int updateSql(String sql) {
        System.out.println(sql);
        int rows = 0;
        try {
            createConnection();
            rows = stmt.executeUpdate(sql);

            // 5. 关闭连接
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

    /**
     * 获取 SystemUsers 列表
     * @param sql 数据库查询语句
     * @return List<SystemUsers>
     */
    private List<SystemUsers> getSystemUsers(String sql) {
        System.out.println(sql);
        createConnection();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // 遍历结果集，转换为 SystemUsers 类并依次添加到列表
            users = new ArrayList<>();
            while (rs.next()) {
                // 每次添加一个 SystemUsers 对象
                users.add(new SystemUsers(rs.getString("login_name"), rs.getString("login_password"), null, rs.getString("telephone"), rs.getString("email"),
                        rs.getBoolean("status"), rs.getInt("role_id"), getRole(rs.getInt("role_id"))));
            }
            closeConnection();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return users;
    }

    private Roles getRole(int roleId) {
        Roles role = null;
        try {
            createConnection();

            String sql = String.format("select * from roles where id = %d", roleId);
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                role = new Roles();
                role.setId(roleId);
                role.setRoleName(rs.getString("role_name"));
                role.setComments(rs.getString("comments"));
            }

            // 5. 关闭连接
            closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    public static void main(String[] args) {
        List<SystemUsers> users = new LoginService().getList();

        for (SystemUsers user : users)
            System.out.println(user);

    }
}
