package cn.cccxu.accountservice.dao;

import cn.cccxu.accountservice.entity.LoginInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
}
