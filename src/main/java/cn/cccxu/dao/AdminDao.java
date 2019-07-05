package cn.cccxu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/5
 */

@Mapper
@Component
public interface AdminDao {

    @Select("SELECT password_hash " +
            "FROM tb_admin " +
            "WHERE admin_id = #{adminId}")
    String selectAdminPasswordHash(String adminId);

    @Select("SELECT salt " +
            "FROM tb_admin " +
            "WHERE admin_id = #{adminId}")
    String selectAdminSalt(String adminId);
}
