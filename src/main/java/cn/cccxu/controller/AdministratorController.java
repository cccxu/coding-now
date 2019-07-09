package cn.cccxu.controller;

import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.service.LessonService;
import cn.cccxu.service.TeacherInfoService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@RestController
public class AdministratorController {

    private TeacherInfoService teacherInfoService;
    private LessonService lessonService;

    @Autowired
    AdministratorController(TeacherInfoService mTeacherInfoService, LessonService lessonService) {
        this.teacherInfoService = mTeacherInfoService;
        this.lessonService = lessonService;
    }

    @PostMapping(path="/admin/addTeacher")
    public boolean addTeacher(@RequestBody TeacherInfo teacherInfo,
                              HttpServletRequest request) {
        if(request.getSession().getAttribute("adminId") != null) {
            return teacherInfoService.promoteToTeacher(teacherInfo);
        } else {
            return false;
        }
    }

    @GetMapping(path = "/admin/getLessonIdList")
    public List<String> getAllUnauditLessonId() {
        return lessonService.getAllUnauditLessonId();
    }

    @PostMapping(path = "/admin/auditVideo")
    public boolean auditVideo(@RequestBody JSONObject jsonObject){
        return lessonService.auditVideo(jsonObject.getString("lessonId"),
                jsonObject.getString("videoName"),
                jsonObject.getBoolean("pass"));
    }

    //todo: 删除评论
    //todo: 删除用户
}
