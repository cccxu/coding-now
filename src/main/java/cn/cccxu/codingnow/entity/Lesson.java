package cn.cccxu.codingnow.entity;

import java.sql.Date;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

public class Lesson {

    private String lessonId;  //16位数字+字母
    private String lessonTitle;
    private String teacherId;
    private Date uploadTime;  //由服务器确定，只保存日期，不保存时间，按照开始上传的日期确定
    private String introduction;
    private String rootPath;  //本课程视频的ROOT路径
    private int sectorAccount;  //课程总的课时数

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public int getSectorAccount() {
        return sectorAccount;
    }

    public void setSectorAccount(int sectorAccount) {
        this.sectorAccount = sectorAccount;
    }
}
