package cn.cccxu.controller;

import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.model.Teacher;
import cn.cccxu.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@RestController
public class TeacherInfoController {

    private TeacherInfoService teacherInfoService;

    @Autowired
    TeacherInfoController(TeacherInfoService teacherInfoService) {
        this.teacherInfoService = teacherInfoService;
    }

    @GetMapping(path="/info/getTeacherInfo")
    public Teacher getTeacherInfo(@RequestParam String teacher_id) {
        return teacherInfoService.getTeacherInfo(teacher_id);
    }
}
