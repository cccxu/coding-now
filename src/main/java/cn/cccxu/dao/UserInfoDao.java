package cn.cccxu.dao;

import cn.cccxu.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Mapper
@Component
public interface UserInfoDao {

    String TABLE_NAME = " tb_user_info ";
    String NEW_USER_INFO = " user_id, user_nick_name, head_pic, email, gender, phone_number ";

    @Insert({"INSERT INTO ", TABLE_NAME, "(", NEW_USER_INFO, ")" +
            " values (#{userId}, #{userNickName}, #{headPic}, #{email}, #{gender}, #{phoneNumber})"})
    boolean insertUserInfo(UserInfo userInfo);

    @Select({"SELECT * FROM ", TABLE_NAME,
            "WHERE user_id = #{userId}"})
    UserInfo selectUserInfo(String userId);

    @Update({"UPDATE ", TABLE_NAME,
             "SET " +
                 "user_nick_name = #{userNickName}, " +
                 "head_pic = #{headPic}, " +
                 "email = #{email}, " +
                 "gender = #{gender}, " +
                 "phone_number = #{phoneNumber}" +
             "WHERE " +
                     "user_id = #{userId}"})
    boolean updateUserInfo(UserInfo userInfo);

    @Update({"UPDATE ", TABLE_NAME,
            "SET " +
                "head_pic = #{headImage}" +
            "WHERE " +
                "user_id = #{userId}"})
    boolean updateUserHeadImage(String headImage, String userId);
}
