package cn.cccxu.dao;

import cn.cccxu.entity.Followers;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

@Mapper
@Component
public interface FollowersDao {

    //关注

    @Insert("INSERT INTO tb_followers " +
            "(followed_id, follower_id) " +
            "VALUES (#{followedId}, #{followerId})")
    boolean insertFollow(Followers followers);

    //取消关注
    @Delete("DELETE FROM tb_followers " +
            "WHERE followed_id = #{followedId} AND follower_id = #{followerId}")
    boolean deleteFollow(Followers followers);

    //获取关注列表
    @Select("SELECT followed_id " +
            "FROM tb_followers " +
            "WHERE follower_id = #{followerId}")
    List<String> selectFollowed(String followerId);
}
