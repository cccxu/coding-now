package cn.cccxu.entity;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

public class Followers {

    private String followedId;
    private String followerId;

    public String getFollowedId() {
        return followedId;
    }

    public void setFollowedId(String followedId) {
        this.followedId = followedId;
    }

    public String getFollowerId() {
        return followerId;
    }

    public void setFollowerId(String followerId) {
        this.followerId = followerId;
    }
}
