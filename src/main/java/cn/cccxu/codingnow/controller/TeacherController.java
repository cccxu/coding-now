package cn.cccxu.codingnow.controller;

import cn.cccxu.codingnow.entity.TeacherInfo;
import cn.cccxu.codingnow.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class TeacherController {

    private TeacherService teacherService;

    @Autowired
    TeacherController(TeacherService mTeacherService) {
        this.teacherService = mTeacherService;
    }

    @PostMapping(path="/teacher/addTeacher")
    @ResponseBody
    public boolean addTeacher(@RequestBody TeacherInfo teacherInfo) {
        return teacherService.promoteToTeacher(teacherInfo);
    }
}
