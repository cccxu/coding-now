package cn.cccxu.service;

import cn.cccxu.dao.AdminDao;
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
    private AdminDao adminDao;

    @Autowired
    LoginService(LoginInfoDao mLoginInfoDao, TeacherDao teacherDao, AdminDao adminDao) {
        this.loginInfoDao = mLoginInfoDao;
        this.teacherDao = teacherDao;
        this.adminDao = adminDao;
    }

    public String getSalt(String userId){
        return loginInfoDao.checkUserLogin(userId).getSalt();
    }

    public boolean checkUserLogin(String userId, String passwordHash){
        if(loginInfoDao.selectUsrBan(userId)!=null){
            return false;
        } else {
            return loginInfoDao.checkUserLogin(userId).getPasswordHash().equals(passwordHash);
        }
    }

    //教师登录，检查用户名和密码，然后检查是否为教师
    public boolean checkTeacherLogin(String userId, String passwordHash) {
        return checkUserLogin(userId, passwordHash) & teacherDao.selectTeacherId(userId) != null;
    }


    //管理员获取salt
    public String getAdminSalt(String adminId) {
        return adminDao.selectAdminSalt(adminId);
    }


    //管理员登录，检查管理员表中的用户名和密码
    public boolean checkAdminLogin(String adminId, String passwordHash) {
        return adminDao.selectAdminPasswordHash(adminId).equals(passwordHash);
    }

    //禁止用户登录
    public boolean banUser(String userId) {
        return loginInfoDao.insertUserBan(userId);
    }
}
