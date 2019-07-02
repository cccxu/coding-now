package cn.cccxu.codingnow.dao;

import cn.cccxu.codingnow.entity.Lesson;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
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
                "(lesson_id, lesson_title, teacher_id, upload_time, introduction, root_path, sector_account)" +
            "VALUES" +
                "(#{lessonId}, #{lessonTitle}, #{teacherId}, #{uploadTime}, #{introduction}, #{rootPath}, #{sectorAccount})")
    boolean insertLesson(Lesson lesson);

    @Select("SELECT " +
                "lesson_title " +
            "FROM " +
                "tb_lesson " +
            "WHERE " +
                "lesson_id = #{0}")
    String checkLessonIdUseable(String lesson_id);

    @Select("SELECT " +
                "* " +
            "WHERE " +
                "teacher_id = #{0}")
    List<Lesson> getTeacherLessons(String teacherId);

}
