package cn.cccxu.codingnow.model;

import cn.cccxu.codingnow.entity.LoginInfo;
import cn.cccxu.codingnow.entity.LoginSafe;
import cn.cccxu.codingnow.entity.UserInfo;

/**
 * @author 徐浩
 * created at 2019/06/29
 */
public class User {

    private LoginInfo loginInfo;
    private LoginSafe loginSafe;
    private UserInfo userInfo;

    public User(LoginInfo loginInfo, LoginSafe loginSafe, UserInfo userInfo) {
        this.loginInfo = loginInfo;
        this.loginSafe = loginSafe;
        this.userInfo = userInfo;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public LoginSafe getLoginSafe() {
        return loginSafe;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }
}
