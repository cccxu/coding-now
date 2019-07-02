package cn.cccxu.codingnow.dao;

import cn.cccxu.codingnow.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
}
