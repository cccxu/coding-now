package cn.cccxu.dao;

import cn.cccxu.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/6
 * 功能：
 *  1. 插入新评论
 *  2. 获取所有评论
 *  3. 点赞
 */

@Mapper
@Component
public interface CommentDao {

    //插入新评论
    @Insert("INSERT INTO tb_comment " +
            "(from_user_id, in_lesson_id, content) " +
            "VALUES (#{fromUserId}, #{inLessonId}, #{content})")
    boolean insertComment(Comment comment);

    //回复评论
    @Insert("INSERT INTO tb_comment " +
            "(from_user_id, to_user_id, in_lesson_id, content, father_id) " +
            "VALUES (#{fromUserId}, #{toUserId}, #{inLessonId}, #{content}, #{fatherId})")
    boolean insertreplyComment(Comment comment);

    //获取所有评论
    @Select("SELECT * " +
            "FROM tb_comment " +
            "WHERE in_lesson_id = #{lessonId}")
    List<Comment> selectAllComment(String lessonId);

    //点赞
    @Update("UPDATE tb_comment " +
            "SET comment_like = comment_like + 1 " +
            "WHERE comment_id = #{commentId}")
    boolean updateLike(long commentId);

    //todo: 根据用户id查看发言
}
