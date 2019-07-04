package cn.cccxu.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

public class PersonalCollection {

    private String userId;
    private String lessonId;
    private Timestamp collectDate;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public Timestamp getCollectDate() {
        return collectDate;
    }

    public void setCollectDate(Timestamp collectDate) {
        this.collectDate = collectDate;
    }
}
