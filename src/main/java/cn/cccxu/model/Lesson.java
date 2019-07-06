package cn.cccxu.model;

import cn.cccxu.entity.LessonCollect;
import cn.cccxu.entity.LessonInfo;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/6
 */

public class Lesson {

    private LessonInfo lessonInfo;
    private LessonCollect lessonCollect;

    public Lesson(LessonInfo lessonInfo, LessonCollect lessonCollect){
        this.lessonInfo = lessonInfo;
        this.lessonCollect = lessonCollect;
    }

    public LessonInfo getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public LessonCollect getLessonCollect() {
        return lessonCollect;
    }

    public void setLessonCollect(LessonCollect lessonCollect) {
        this.lessonCollect = lessonCollect;
    }
}
