package cn.cccxu.dao;

import cn.cccxu.entity.LessonCollect;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/5
 */

@Mapper
@Component
public interface LessonCollectDao {

    //修改课程收藏信息
    @Update("UPDATE tb_lesson_collect " +
            "SET collected_times = #{collectedTimes} " +
            "WHERE lesson_id = #{lessonId}")
    boolean updateLessonCollect(LessonCollect lessonCollect);

    //新建课程后同步在这张表里创建课程收藏信息
    @Insert("INSERT INTO tb_lesson_collect " +
            "(lesson_id) " +
            "VALUES(#{lessonId})")
    boolean insertLessonCollct(String lessonId);

    //获取课程收藏次数
    @Select("SELECT collected_times " +
            "FROM tb_lesson_collect " +
            "WHERE lesson_id = #{lessonId}")
    int selectLessonCollectTimes(String lessonId);

    //点赞
    @Update("UPDATE tb_lesson_collect " +
            "SET `like` = #{like} " +
            "WHERE lesson_id = #{lessonId}")
    boolean updateLike(String lessonId, int like);

    //获取点赞数
    @Select("SELECT `like` " +
            "FROM tb_lesson_collect " +
            "WHERE lesson_id = #{lessonId}")
    int getLike(String lessonId);

    //点踩
    @Update("UPDATE tb_lesson_collect " +
            "SET dislike = #{dislike} " +
            "WHERE lesson_id = #{lessonId}")
    boolean updateDislike(String lessonId, int dislike);

    //获取点踩数
    @Select("SELECT dislike " +
            "FROM tb_lesson_collect " +
            "WHERE lesson_id = #{lessonId}")
    int getDislike(String lessonId);

    //获取课程收藏信息
    @Select("SELECT * " +
            "FROM tb_lesson_collect " +
            "WHERE lesson_id = #{lessonId}")
    LessonCollect selectLessonCollectInfo(String lessonId);

    //获取收藏次数前10的课程
    @MapKey("lesson_id")
    @Select("SELECT * " +
            "FROM tb_lesson_collect " +
            "ORDER BY collected_times DESC " +
            "limit 10")
    List<LessonCollect> selectTopCollected();

    //获取点赞次数前10的课程
    @MapKey("lesson_id")
    @Select("SELECT * " +
            "FROM tb_lesson_collect " +
            "ORDER BY `like` DESC " +
            "limit 10")
    List<LessonCollect> selectTopLiked();
}
