package cn.cccxu.accountservice.entity;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

public class LoginInfo {
    //存储在用户密码表
    private String userId;
    private String passwordHash;
    private String salt;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
