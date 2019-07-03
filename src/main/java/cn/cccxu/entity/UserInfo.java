package cn.cccxu.entity;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

public class UserInfo {
    //存储在用户信息表
    private String userId;  //用户账号，由用户自定义，使用数字和字母组成，不区分大小写，不可以重复
    private String userNickName;  //用户昵称
    private String headPic;  //头像的存储路径，默认头像使用nowcoder的头像生成
    private String email;  //邮箱，默认为Null
    private Boolean gender;  //性别，默认位null
    private long phoneNumber;  //手机号码，默认为null
    private int memberPoints;  //用户签到积分，初始值为1

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(int memberPoints) {
        this.memberPoints = memberPoints;
    }
}
