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
                "(lesson_id, lesson_title, teacher_id, introduction, root_path, sector_account, image_name) " +
            " VALUES" +
                "(#{lessonId}, #{lessonTitle}, #{teacherId}, #{introduction}, #{rootPath}, #{sectorAccount}, #{imageName})")
    boolean insertLesson(LessonInfo lessonInfo);

    @Select("SELECT " +
                "lesson_title " +
            "FROM " +
                "tb_lesson_info " +
            "WHERE " +
                "lesson_id = #{lessonId}")
    String checkLessonIdUseable(String lessonId);

    @Select("SELECT * " +
                "FROM tb_lesson_info" +
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

    //获取所有课程列表
    @Select("SELECT * " +
            "FROM tb_lesson_info ")
    List<LessonInfo> selectAllLesson();
}
