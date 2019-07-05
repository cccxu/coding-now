package cn.cccxu.dao;

import cn.cccxu.entity.Lesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Mapper
@Component
public interface LessonDao {

    @Insert("INSERT INTO tb_lesson" +
                "(lesson_id, lesson_title, teacher_id, upload_time, introduction, root_path, sector_account) " +
            " VALUES" +
                "(#{lessonId}, #{lessonTitle}, #{teacherId}, #{uploadTime}, #{introduction}, #{rootPath}, #{sectorAccount})")
    boolean insertLesson(Lesson lesson);

    @Select("SELECT " +
                "lesson_title " +
            "FROM " +
                "tb_lesson " +
            "WHERE " +
                "lesson_id = #{lessonId}")
    String checkLessonIdUseable(String lessonId);

    @Select("SELECT " +
                " * " +
            "WHERE " +
                "teacher_id = #{teacherId}")
    List<Lesson> selectTeacherLessons(String teacherId);

    @Select("SELECT " +
                "root_path " +
            "FROM " +
                "tb_lesson " +
            "WHERE " +
                "lesson_id = #{lessonId}")
    String selectLessonStoreLocal(String lessonId);
}
