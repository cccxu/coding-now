package cn.cccxu.codingnow.service;

import cn.cccxu.codingnow.dao.LoginInfoDao;
import cn.cccxu.codingnow.dao.LoginSafeDao;
import cn.cccxu.codingnow.entity.LoginInfo;
import cn.cccxu.codingnow.entity.LoginSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Service
public class LoginSafeService {

    private LoginSafeDao loginSafeDao;
    private LoginInfoDao loginInfoDao;

    @Autowired
    LoginSafeService(LoginSafeDao mLoginSafeDao, LoginInfoDao mLoginInfoDao){
        this.loginSafeDao = mLoginSafeDao;
        this.loginInfoDao = mLoginInfoDao;
    }

    public boolean checkLoginSafe(LoginSafe loginSafe) {
        LoginSafe iLoginSafe = loginSafeDao.selectLoginSafe(loginSafe.getUserId());
        return loginSafe.getUserId().equals(iLoginSafe.getUserId()) &
                loginSafe.getAnswer().equals(iLoginSafe.getAnswer()) &
                loginSafe.getQuestionId() == iLoginSafe.getQuestionId();
    }

    public boolean changePassword(LoginInfo loginInfo) {
        return loginInfoDao.changePassword(loginInfo);
    }
}
