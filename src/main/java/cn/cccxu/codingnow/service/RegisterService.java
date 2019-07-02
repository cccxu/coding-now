package cn.cccxu.codingnow.service;

import cn.cccxu.codingnow.dao.LoginInfoDao;
import cn.cccxu.codingnow.dao.LoginSafeDao;
import cn.cccxu.codingnow.dao.UserInfoDao;
import cn.cccxu.codingnow.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Service
public class RegisterService {

    private LoginInfoDao loginInfoDao;
    private LoginSafeDao loginSafeDao;
    private UserInfoDao userInfoDao;

    @Autowired
    RegisterService(LoginInfoDao mloginInfoDao,
                    LoginSafeDao mloginSafeDao,
                    UserInfoDao muserInfoDao){
        this.loginInfoDao = mloginInfoDao;
        this.loginSafeDao = mloginSafeDao;
        this.userInfoDao = muserInfoDao;
    }

    public boolean register(User user) {
        return userInfoDao.insertUserInfo(user.getUserInfo()) &
                loginInfoDao.insertLoginInfo(user.getLoginInfo()) &
                loginSafeDao.insertLoginSafeInfo(user.getLoginSafe());
    }

    public boolean checkUserIdUseable(String userId) {
        return loginInfoDao.checkUserIdUsable(userId) == null;
    }
}
