package cn.cccxu.dao;

import cn.cccxu.entity.LessonInfo;
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
public interface LessonInfoDao {

    @Insert("INSERT INTO tb_lesson_info" +
                "(lesson_id, lesson_title, teacher_id, upload_time, introduction, root_path, sector_account) " +
            " VALUES" +
                "(#{lessonId}, #{lessonTitle}, #{teacherId}, #{uploadTime}, #{introduction}, #{rootPath}, #{sectorAccount})")
    boolean insertLesson(LessonInfo lessonInfo);

    @Select("SELECT " +
                "lesson_title " +
            "FROM " +
                "tb_lesson_info " +
            "WHERE " +
                "lesson_id = #{lessonId}")
    String checkLessonIdUseable(String lessonId);

    @Select("SELECT " +
                " * " +
            "WHERE " +
                "teacher_id = #{teacherId}")
    List<LessonInfo> selectTeacherLessons(String teacherId);

    @Select("SELECT " +
                "root_path " +
            "FROM " +
                "tb_lesson_info " +
            "WHERE " +
                "lesson_id = #{lessonId}")
    String selectLessonStoreLocal(String lessonId);

    @Select("SELECT teacher_id " +
            "FROM tb_lesson_info " +
            "WHERE lesson_id = #{lessonId}")
    String selectLessonTeacher(String lessonId);

    //获取课程信息
    @Select("SELECT * " +
            "FROM tb_lesson_info " +
            "WHERE lesson_id = #{lessonId}")
    LessonInfo selectLessonInfo(String lessonId);
}
