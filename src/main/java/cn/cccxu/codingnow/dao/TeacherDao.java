package cn.cccxu.codingnow.dao;

import cn.cccxu.codingnow.entity.TeacherInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Mapper
@Component
public interface TeacherDao {

    @Insert("INSERT INTO tb_teacher(teacher_id, real_name, teacher_introduction) VALUES(#{teacherId}, #{realName}, #{teacherIntroduction})")
    boolean insertTeacher(TeacherInfo teacherInfo);
}