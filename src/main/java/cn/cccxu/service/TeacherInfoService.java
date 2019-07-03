package cn.cccxu.service;

import cn.cccxu.dao.TeacherDao;
import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.model.Teacher;
import cn.cccxu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Service
public class TeacherInfoService {

    private TeacherDao teacherDao;
    private  UserInfoService userInfoService;

    @Autowired
    TeacherInfoService(TeacherDao mTeacherDao, UserInfoService userInfoService) {
        this.teacherDao = mTeacherDao;
        this.userInfoService = userInfoService;
    }

    public boolean promoteToTeacher(TeacherInfo teacherInfo) {
        try{
            return teacherDao.insertTeacher(teacherInfo);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Teacher getTeacherInfo(String teacher_id){
        return new Teacher(
                new User(null, null, userInfoService.getUserInfo(teacher_id)),
                teacherDao.selectTeacherInfo(teacher_id));
    }
}
