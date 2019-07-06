package cn.cccxu.entity;

import java.sql.Date;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/6
 */

public class Comment {

    private long commentId;
    private String fromUserId;
    private String toUserId;
    private String inLessonId;
    private String content;
    private Date commentDate;
    private int commentLike;
    private long fatherId;

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getInLessonId() {
        return inLessonId;
    }

    public void setInLessonId(String inLessonId) {
        this.inLessonId = inLessonId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public int getCommentLike() {
        return commentLike;
    }

    public void setCommentLike(int commentLike) {
        this.commentLike = commentLike;
    }

    public long getFatherId() {
        return fatherId;
    }

    public void setFatherId(long fatherId) {
        this.fatherId = fatherId;
    }
}
