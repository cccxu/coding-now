package cn.cccxu.entity;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

public class LoginSafe {
    //存储在用户密保问题表
    private String userId;
    private int questionId;
    private String answer;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
