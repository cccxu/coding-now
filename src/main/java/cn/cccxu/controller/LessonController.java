package cn.cccxu.controller;

import cn.cccxu.entity.LessonCollect;
import cn.cccxu.entity.LessonInfo;
import cn.cccxu.model.Lesson;
import cn.cccxu.service.LessonService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@RestController
public class LessonController {

    private LessonService lessonService;
    Logger logger = LoggerFactory.getLogger(LessonController.class);

    @Autowired
    LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    /**
     * @param file
     * @return 操作结果
     * 修改：
     *  1. 改用session存储的教师id
     */
    @PostMapping(path = "/lesson/addLesson")
    public boolean addLesson(@RequestParam MultipartFile file,
                             @RequestParam String lessonId,
                             @RequestParam String lessonTitle,
                             @RequestParam String introduction,
                             @RequestParam int sectorAccount,
                             HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("userType")!=null && httpSession.getAttribute("userType").equals("teacher")){
            LessonInfo lessonInfo = new LessonInfo();

            lessonInfo.setTeacherId((String) httpSession.getAttribute("userId"));
            lessonInfo.setLessonId(lessonId);
            lessonInfo.setIntroduction(introduction);
            lessonInfo.setLessonTitle(lessonTitle);
            lessonInfo.setSectorAccount(sectorAccount);

            return lessonService.addNewLesson(file, lessonInfo);
        } else {
            return false;
        }

    }

    //获取课程信息，返回Lesson对象
    @GetMapping(path = "/lesson/getLessonInfo")
    public Lesson getLessonInfo(@RequestParam String lessonId) {
        return lessonService.getLessonInfo(lessonId);
    }

    @PostMapping(path = "/lesson/like")
    public boolean likeLesson(@RequestBody JSONObject jsonObject,
                              HttpServletRequest request) {
        if(request.getSession().getAttribute("userType").equals("user")) {
            return lessonService.likeLesson(jsonObject.getString("lessonId"));
        } else {
            return false;
        }
    }

    @PostMapping(path = "/lesson/dislike")
    public boolean dislikeLesson(@RequestBody JSONObject jsonObject,
                                 HttpServletRequest request) {
        if(request.getSession().getAttribute("userType").equals("user")) {
            return lessonService.dislikeLesson(jsonObject.getString("lessonId"));
        } else {
            return false;
        }
    }

    @PostMapping(path = "/lesson/uploadVideo/{lessonId}")
    public boolean uploadVideo(@RequestParam Map<String, MultipartFile> files,
                               @PathVariable("lessonId") String lessonId,
                               HttpServletRequest request) {

        String teacherId = (String)request.getSession().getAttribute("userId");

        if(!lessonService.getLessonTeacher(lessonId).equals(teacherId)){
            logger.info("++++++++教师ID不匹配----" + lessonService.getLessonTeacher(lessonId) + "----" + teacherId);
            return false;
        } else {
            return lessonService.uploadVideo(files, teacherId, lessonId);
        }
    }

    @GetMapping(path = "/lessons/{lessonId}")
    public List<String> getVideoList(@PathVariable("lessonId") String lessonId) {
        return lessonService.getVideoList(lessonId);
    }

    @GetMapping(path = "/lessons/getTopCollected")
    public List<JSONObject> getTopCollected() {
        return lessonService.getTopCollected();
    }

    @GetMapping(path = "/lessons/getTopLiked")
    public List<JSONObject> getTopLiked() {
        return lessonService.getTopLiked();
    }

    //根据教师ID获取课程列表
   @GetMapping(path = "/lessons/getTeacherLesson")
   public List<LessonInfo> getTeacherLesson(HttpServletRequest request) {
        return lessonService.getTeacherLesson(request.getSession().getAttribute("userId").toString());
   }

   //搜索
    @GetMapping(path = "/lessons/search")
    public List<JSONObject> searchLesson(@RequestParam String keyword) {
        return lessonService.searchLesson(keyword);
    }

}
