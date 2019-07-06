package cn.cccxu.entity;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/5
 */

public class LessonCollect {

    private String lessonId;
    private int collectedTimes;
    private int like;
    private int dislike;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public int getCollectedTimes() {
        return collectedTimes;
    }

    public void setCollectedTimes(int collectedTimes) {
        this.collectedTimes = collectedTimes;
    }
}
