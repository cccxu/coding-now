package cn.cccxu.controller;

import cn.cccxu.entity.Lesson;
import cn.cccxu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * @param lesson 课程的教师id不再使用
     * @return 操作结果
     * 修改：
     *  1. 改用session存储的教师id
     */
    @PostMapping(path = "/lesson/addLesson/")
    @ResponseBody
    public boolean addLesson(Lesson lesson,
                             HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("userType").equals("teacher")){
            lesson.setTeacherId((String) httpSession.getAttribute("userId"));
            return lessonService.addNewLesson(lesson);
        } else {
            return false;
        }

    }


    @PostMapping(path = "/lesson/uploadVideo/{lessonId}")
    @ResponseBody
    public boolean uploadVideo(@RequestParam Map<String, MultipartFile> files,
                               @PathVariable("lessonId") String lessonId,
                               HttpServletRequest request) {

        return lessonService.uploadVideo(files,
                (String)request.getSession().getAttribute("userId"),
                lessonId);
    }
}
