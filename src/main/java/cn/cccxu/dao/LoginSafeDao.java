package cn.cccxu.dao;

import cn.cccxu.entity.LoginSafe;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Mapper
@Component
public interface LoginSafeDao {

    String TABLE_NAME = " tb_login_safe ";
    String LOGIN_SAFE = " user_id, question_id, answer ";

    @Insert({"INSERT INTO ", TABLE_NAME, "(", LOGIN_SAFE, ") " +
            "VALUES(#{userId}, #{questionId}, #{answer})"})
    boolean insertLoginSafeInfo(LoginSafe loginSafe);

    @Select({"SELECT * FROM ", TABLE_NAME,
                "WHERE user_id = #{userId}" })
    LoginSafe selectLoginSafe(String userId);
}
