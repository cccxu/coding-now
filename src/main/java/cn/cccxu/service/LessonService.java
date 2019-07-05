package cn.cccxu.service;

import cn.cccxu.entity.Lesson;
import cn.cccxu.dao.LessonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author 徐浩
 * created at 2019/07/02
 */


@Service
public class LessonService {

    private LessonDao lessonDao;
    private String baseAddr = "/coding-now/resources/lessons/";
    private String auditAddr = "/coding-now/audit-region/";

    @Autowired
    LessonService(LessonDao mLessonDao) {
        this.lessonDao = mLessonDao;
    }

    //检查课程ID是否可用
    public boolean checkLessonIdUseable(String lessonId) {
        return (lessonDao.checkLessonIdUseable(lessonId) == null);
    }

    //新建课程
    //传入参数：Lesson对象
    public boolean addNewLesson(Lesson lesson) {
        //课程存储的路径
        String rootPath = baseAddr + lesson.getLessonId();
        String auditPath = auditAddr + lesson.getLessonId();
        //创建课程路径，lesson文件夹名称为lessonId
        //创建课程审核路径
        File file = new File(rootPath);
        file.mkdir();
        File auditFile = new File(auditPath);
        auditFile.mkdir();
        //设置lesson的rootPath, 注意，这里要转为url
        String url = "/lessons/" + lesson.getLessonId() + "/";
        lesson.setRootPath(url);
        //写入数据库
        return lessonDao.insertLesson(lesson);
    }

    //上传课程视频
    //传入参数：课程视频文件 MAP ，教师Id, 课程id
    public boolean uploadVideo(Map<String, MultipartFile> files, String userId, String lessonId) {

        //首先检查lesson是否属于本用户

        //遍历文件并分别重命名和存放
        for(String fileName : files.keySet()){
            try {
                byte[] bytes = files.get(fileName).getBytes();
                //上传后放到待审核文件夹
                String storeLocal = auditAddr + lessonId;
                //保证扩展名不变
                int lastIndexOfFileName = files.get(fileName).getOriginalFilename().lastIndexOf(".");
                Path paths = Paths.get(storeLocal +
                        fileName +
                        files.get(fileName).getOriginalFilename().substring(lastIndexOfFileName));

                Files.write(paths, bytes, StandardOpenOption.CREATE);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    //审核课程
    //传入参数：课程id, 审核课程的名称（带扩展名）, 审核是否通过
    public boolean auditVideo(String lessonId, String videoName, boolean pass) {
        //获取文件
        File oldFile = new File(auditAddr + lessonId + "/" + videoName);
        File newFile = new File(baseAddr + lessonId + "/" + videoName);
        if(pass) {
            return oldFile.renameTo(newFile);
        } else {
            return oldFile.delete();
        }


    }

    //获取课程视频列表
    //传入参数：课程id
    public List<String> getVideoList(String lessonId) {

        List<String> fileListName = new ArrayList<>();

        //获取课程根目录
        Path path = Paths.get(baseAddr + lessonId);
        File[] fileList = path.toFile().listFiles();

        if(fileList == null){
            return null;
        }

        for(File file : fileList){
            fileListName.add(file.getName());
        }

        return fileListName;
    }
}
