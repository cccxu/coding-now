package cn.cccxu.accountservice.model;

import cn.cccxu.accountservice.entity.LoginInfo;
import cn.cccxu.accountservice.entity.LoginSafe;
import cn.cccxu.accountservice.entity.UserInfo;

/**
 * @author 徐浩
 * created at 2019/06/29
 */
public class User {

    private LoginInfo loginInfo;
    private LoginSafe loginSafe;
    private UserInfo userInfo;

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
