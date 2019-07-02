package cn.cccxu.codingnow.entity;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

public class TeacherInfo {

    private String teacherId;
    private String realName;
    private String teacherIntroduction;

    public String getTeacherIntroduction() {
        return teacherIntroduction;
    }

    public void setTeacherIntroduction(String teacherIntroduction) {
        this.teacherIntroduction = teacherIntroduction;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
