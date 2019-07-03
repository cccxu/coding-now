package cn.cccxu.model;

import cn.cccxu.entity.TeacherInfo;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

public class Teacher {

    private User user;
    private TeacherInfo teacherInfo;

    public Teacher(User user, TeacherInfo teacherInfo) {
        this.user = user;
        this.teacherInfo = teacherInfo;
    }

    public User getUser() {
        return user;
    }

    public TeacherInfo getTeacher() {
        return teacherInfo;
    }
}
