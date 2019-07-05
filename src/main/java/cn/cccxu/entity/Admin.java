package cn.cccxu.entity;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/5
 */

public class Admin {

    private String admin_id;
    private String passwordHash;
    private String salt;

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
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
