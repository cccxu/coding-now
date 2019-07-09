package cn.cccxu.controller;

import cn.cccxu.entity.LessonInfo;
import cn.cccxu.entity.TeacherInfo;
import cn.cccxu.service.CommentService;
import cn.cccxu.service.LessonService;
import cn.cccxu.service.LoginService;
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
public class AdminController {

    private TeacherInfoService teacherInfoService;
    private LessonService lessonService;
    private CommentService commentService;
    private LoginService loginService;

    @Autowired
    AdminController(TeacherInfoService mTeacherInfoService,
                    LessonService lessonService,
                    CommentService commentService,
                    LoginService loginService) {
        this.teacherInfoService = mTeacherInfoService;
        this.lessonService = lessonService;
        this.commentService = commentService;
        this.loginService = loginService;
    }

    @PostMapping(path="/admin/addTeacher")
    public boolean addTeacher(@RequestBody TeacherInfo teacherInfo,
                              HttpServletRequest request) {
        if(!request.getSession().getAttribute("userType").equals("admin")) {
            return teacherInfoService.promoteToTeacher(teacherInfo);
        } else {
            return false;
        }
    }

    @GetMapping(path = "/admin/getTeacherList")
    public List<TeacherInfo> getTeacherList(HttpServletRequest request) {
        if(!request.getSession().getAttribute("userType").equals("admin")) {
            return teacherInfoService.getAllTeacher();
        } else {
            return null;
        }
    }

    @GetMapping(path = "/admin/getLessonIdList")
    public List<LessonInfo> getAllUnauditLessonId(HttpServletRequest request) {
        if(!request.getSession().getAttribute("userType").equals("admin")){
            return null;
        } else {
            return lessonService.getAllUnauditLessonId();
        }
    }

    @GetMapping(path = "/admin/getAllUnauditVideoList")
    public List<String> getAllUnauditVideoList(@RequestParam String lessonId) {
        return lessonService.getAllUnauditVideo(lessonId);
    }

    @PostMapping(path = "/admin/auditVideo")
    public boolean auditVideo(@RequestBody JSONObject jsonObject,
                              HttpServletRequest request){
        if(!request.getSession().getAttribute("userType").equals("admin")){
            return false;
        } else {
        return lessonService.auditVideo(jsonObject.getString("lessonId"),
                jsonObject.getString("videoName"),
                jsonObject.getBoolean("pass"));
        }
    }

    @GetMapping(path = "/admin/deletComment")
    public boolean deleteComment(@RequestParam long commentId,
                                 HttpServletRequest request) {
        if(!request.getSession().getAttribute("userType").equals("admin")){
            return false;
        } else {
            return commentService.deleteComment(commentId);
        }
    }

    @GetMapping(path="/admin/banUser")
    public boolean banUser(@RequestParam String userId,
                           HttpServletRequest request) {
        if(!request.getSession().getAttribute("userType").equals("admin")){
            return false;
        } else {
            return loginService.banUser(userId);
        }
    }
}
