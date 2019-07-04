package cn.cccxu.service;

import cn.cccxu.entity.Lesson;
import cn.cccxu.dao.LessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


/**
 * @author 徐浩
 * created at 2019/07/02
 */


@Service
public class LessonService {

    private LessonDao lessonDao;

    @Autowired
    LessonService(LessonDao mLessonDao) {
        this.lessonDao = mLessonDao;
    }

    public boolean checkLessonIdUseable(String lessonId) {
        return (lessonDao.checkLessonIdUseable(lessonId) == null);
    }


    public boolean addNewLesson(Lesson lesson) {
        //课程存储的路径
        String rootPath = "/coding-now/resources/lessons/" + lesson.getLessonId();
        //创建课程路径，lesson文件夹名称为lessonId
        File file = new File(rootPath);
        file.mkdir();
        //设置lesson的rootPath
        lesson.setRootPath(rootPath);
        //写入数据库
        return lessonDao.insertLesson(lesson);
    }
}
