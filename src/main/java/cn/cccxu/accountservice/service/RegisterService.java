package cn.cccxu.accountservice.service;

import cn.cccxu.accountservice.dao.LoginInfoDao;
import cn.cccxu.accountservice.dao.LoginSafeDao;
import cn.cccxu.accountservice.dao.UserInfoDao;
import cn.cccxu.accountservice.model.User;
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
        return loginInfoDao.insertLoginInfo(user.getLoginInfo()) &
                loginSafeDao.insertLoginSafeInfo(user.getLoginSafe()) &
                userInfoDao.insertUserInfo(user.getUserInfo());
    }
}
