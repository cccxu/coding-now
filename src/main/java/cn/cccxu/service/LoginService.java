package cn.cccxu.service;

import cn.cccxu.dao.LoginInfoDao;
import cn.cccxu.dao.TeacherDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */



@Service
public class LoginService {

    private LoginInfoDao loginInfoDao;
    private TeacherDao teacherDao;

    @Autowired
    LoginService(LoginInfoDao mLoginInfoDao, TeacherDao teacherDao) {
        this.loginInfoDao = mLoginInfoDao;
        this.teacherDao = teacherDao;
    }

    public String getSalt(String userId){
        return loginInfoDao.checkUserLogin(userId).getSalt();
    }

    public boolean checkUserLogin(String userId, String passwordHash){
        return loginInfoDao.checkUserLogin(userId).getPasswordHash().equals(passwordHash);
    }

    //教师登录，检查用户名和密码，然后检查是否为教师
    public boolean checkTeacherLogin(String userId, String passwordHash) {
        return checkUserLogin(userId, passwordHash) & teacherDao.selectTeacherId(userId) != null;
    }
}
