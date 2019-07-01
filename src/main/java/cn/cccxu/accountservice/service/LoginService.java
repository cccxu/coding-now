package cn.cccxu.accountservice.service;

import cn.cccxu.accountservice.dao.LoginInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Service
public class LoginService {

    private LoginInfoDao loginInfoDao;

    @Autowired
    LoginService(LoginInfoDao mLoginInfoDao) {
        this.loginInfoDao = mLoginInfoDao;
    }

    public String getSalt(String userId){
        return loginInfoDao.checkUserLogin(userId).getSalt();
    }

    public boolean checkUserLogin(String userId, String passwordHash){
        return loginInfoDao.checkUserLogin(userId).getPasswordHash().equals(passwordHash);
    }
}
