package cn.cccxu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Mapper
@Component
public interface CheckDao {

    @Update({"UPDATE tb_user_info SET member_points = #{memberPoints} WHERE user_id = #{userId}"})
    boolean setMemberPoints(int memberPoints, String userId);

    @Select("SELECT member_points FROM tb_user_info WHERE user_id = #{userId}")
    int getMemberPoints(String userId);
}
