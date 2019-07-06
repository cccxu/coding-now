package cn.cccxu.service;

import cn.cccxu.dao.LoginInfoDao;
import cn.cccxu.dao.LoginSafeDao;
import cn.cccxu.entity.LoginInfo;
import cn.cccxu.entity.LoginSafe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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
        try {
            //String answer = new String(iLoginSafe.getAnswer().getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
            iLoginSafe.setAnswer(loginSafe.getAnswer());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return loginSafe.getUserId().equals(iLoginSafe.getUserId()) &
                loginSafe.getAnswer().equals(iLoginSafe.getAnswer()) &
                loginSafe.getQuestionId() == iLoginSafe.getQuestionId();
    }

    public boolean changePassword(LoginInfo loginInfo) {
        return loginInfoDao.changePassword(loginInfo);
    }
}
