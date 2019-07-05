package cn.cccxu.controller;

import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.service.TeacherInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class AdministratorController {

    private TeacherInfoService teacherInfoService;

    @Autowired
    AdministratorController(TeacherInfoService mTeacherInfoService) {
        this.teacherInfoService = mTeacherInfoService;
    }

    @PostMapping(path="/teacher/addTeacher")
    @ResponseBody
    public boolean addTeacher(@RequestBody TeacherInfo teacherInfo,
                              HttpServletRequest request) {
        if(request.getSession().getAttribute("adminId") != null) {
            return teacherInfoService.promoteToTeacher(teacherInfo);
        } else {
            return false;
        }
    }
}
