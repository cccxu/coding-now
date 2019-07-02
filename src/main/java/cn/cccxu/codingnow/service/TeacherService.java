package cn.cccxu.codingnow.service;

import cn.cccxu.codingnow.dao.TeacherDao;
import cn.cccxu.codingnow.entity.TeacherInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Service
public class TeacherService {

    private TeacherDao teacherDao;

    @Autowired
    TeacherService (TeacherDao mTeacherDao) {
        this.teacherDao = mTeacherDao;
    }

    public boolean promoteToTeacher(TeacherInfo teacherInfo) {
        try{
            return teacherDao.insertTeacher(teacherInfo);
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
