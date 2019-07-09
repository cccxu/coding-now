package cn.cccxu.dao;

import cn.cccxu.entity.LoginInfo;
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
            " VALUES (#{userId}, #{passwordHash}, #{salt})"})
    boolean insertLoginInfo(LoginInfo loginInfo);

    @Select({"SELECT * FROM", TABLE_NAME,
            " WHERE user_id = #{userId}"})
    LoginInfo checkUserLogin(String userId);

    @Select({"SELECT user_id FROM ", TABLE_NAME,
            " WHERE user_id = #{userId}"})
    String checkUserIdUsable(String userId);

    @Update({"UPDATE ", TABLE_NAME,
            "SET " +
                "password_hash =  #{passwordHash}, salt = #{salt} " +
            "WHERE " +
                "user_id = #{userId}"})
    boolean changePassword(LoginInfo loginInfo);

    //禁止用户登录
    @Insert("INSERT INTO tb_user_ban " +
            "(user_id) " +
            "VALUES (#{userId})")
    boolean insertUserBan(String userId);

    //检查用户是否被ban
    @Select("SELECT * " +
            "FROM tb_user_ban " +
            "WHERE user_id = #{userId}")
    String selectUsrBan(String userId);
}
