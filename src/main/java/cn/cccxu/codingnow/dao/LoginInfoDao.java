package cn.cccxu.codingnow.dao;

import cn.cccxu.codingnow.entity.LoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LoginInfoDao {

    String TABLE_NAME = " tb_login_info ";
    String INSERT_FIELD = " user_id, password_hash, salt ";

    @Insert({"INSERT INTO ", TABLE_NAME, "(", INSERT_FIELD, ") " +
            "VALUES (#{userId}, #{passwordHash}, #{salt})"})
    boolean insertLoginInfo(LoginInfo loginInfo);

    @Select({"SELECT * FROM", TABLE_NAME,
            "WHERE user_id = #{userId}"})
    LoginInfo checkUserLogin(String userId);

    @Select({"SELECT user_id FROM ", TABLE_NAME,
            "WHERE user_id = #{0}"})
    String checkUserIdUsable(String userId);

    @Update({"UPDATE ", TABLE_NAME,
            "SET (password_hash, salt) = (#{passwordHash}, #{salt}) " +
                    "WHERE user_id = #{userId}"})
    boolean changePassword(LoginInfo loginInfo);
}
