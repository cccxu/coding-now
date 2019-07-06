package cn.cccxu.controller;

import cn.cccxu.entity.LessonInfo;
import cn.cccxu.model.Lesson;
import cn.cccxu.service.LessonService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@Controller
public class LessonController {

    private LessonService lessonService;

    @Autowired
    LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * @param lessonInfo 课程的教师id不再使用
     * @return 操作结果
     * 修改：
     *  1. 改用session存储的教师id
     */
    @PostMapping(path = "/lesson/addLesson/")
    @ResponseBody
    public boolean addLesson(@RequestBody LessonInfo lessonInfo,
                             HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("userType").equals("teacher")){
            lessonInfo.setTeacherId((String) httpSession.getAttribute("userId"));
            return lessonService.addNewLesson(lessonInfo);
        } else {
            return false;
        }

    }

    //获取课程信息，返回Lesson对象
    @GetMapping(path = "/lesson/getLessonInfo")
    @ResponseBody
    public Lesson getLessonInfo(@RequestParam String lessonId) {
        return lessonService.getLessonInfo(lessonId);
    }

    @PostMapping(path = "/lesson/like")
    @ResponseBody
    public boolean likeLesson(@RequestBody JSONObject jsonObject,
                              HttpServletRequest request) {
        if(request.getSession().getAttribute("userType").equals("user")) {
            return lessonService.likeLesson(jsonObject.getString("lessonId"));
        } else {
            return false;
        }
    }

    @PostMapping(path = "/lesson/dislike")
    @ResponseBody
    public boolean dislikeLesson(@RequestBody JSONObject jsonObject,
                                 HttpServletRequest request) {
        if(request.getSession().getAttribute("userType").equals("user")) {
            return lessonService.dislikeLesson(jsonObject.getString("lessonId"));
        } else {
            return false;
        }
    }

    @PostMapping(path = "/lesson/uploadVideo/{lessonId}")
    @ResponseBody
    public boolean uploadVideo(@RequestParam Map<String, MultipartFile> files,
                               @PathVariable("lessonId") String lessonId,
                               HttpServletRequest request) {

        String teacherId = (String)request.getSession().getAttribute("userId");

        if(!lessonService.getLessonTeacher(lessonId).equals(teacherId)){
            return false;
        } else {
            return lessonService.uploadVideo(files, teacherId, lessonId);
        }
    }

    @GetMapping(path = "/lessons/{lessonId}")
    @ResponseBody
    public List<String> getVideoList(@PathVariable("lessonId") String lessonId) {
        return lessonService.getVideoList(lessonId);
    }
}
