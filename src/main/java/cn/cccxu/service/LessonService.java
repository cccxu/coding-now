package cn.cccxu.service;

import cn.cccxu.dao.LessonCollectDao;
import cn.cccxu.entity.LessonCollect;
import cn.cccxu.entity.LessonInfo;
import cn.cccxu.dao.LessonInfoDao;
import cn.cccxu.model.Lesson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * @author 徐浩
 * created at 2019/07/02
 */


@Service
public class LessonService {

    private LessonInfoDao lessonInfoDao;
    private LessonCollectDao lessonCollectDao;

    private String baseAddr = "/coding-now/resource/lessons/";
    private String auditAddr = "/coding-now/resource/audit-region/";
    private String rejectAddr = "/coding-now/resource/reject/";

    Logger logger = LoggerFactory.getLogger(LessonService.class);

    @Autowired
    LessonService(LessonInfoDao mLessonInfoDao, LessonCollectDao lessonCollectDao) {
        this.lessonInfoDao = mLessonInfoDao;
        this.lessonCollectDao = lessonCollectDao;
    }

    //检查课程ID是否可用
    public boolean checkLessonIdUseable(String lessonId) {
        return (lessonInfoDao.checkLessonIdUseable(lessonId) == null);
    }

    //新建课程
    //传入参数：Lesson对象
    public boolean addNewLesson(MultipartFile imgFile, LessonInfo lessonInfo) {
        //课程存储的路径
        String rootPath = baseAddr + lessonInfo.getLessonId();
        String auditPath = auditAddr + lessonInfo.getLessonId();
        String rejectPath = rejectAddr + lessonInfo.getLessonId();
        File file = new File(rootPath);
        File auditFile = new File(auditPath);
        File rejectFile = new File(rejectPath);
        boolean mkAuditFile = auditFile.mkdir() ;
        boolean mkFile = file.mkdir();
        boolean mkRejectFile = rejectFile.mkdir();

        //设置lesson的rootPath, 注意，这里要转为url
        String url = "/lessons/" + lessonInfo.getLessonId() + "/";
        lessonInfo.setRootPath(url);
        //存储课程图片
        if(imgFile.isEmpty()){
            return false;
        }

        try {
            byte[] bytes = imgFile.getBytes();
            String storeLocal = baseAddr + lessonInfo.getLessonId() + "/";

            if (imgFile.getOriginalFilename() == null || !imgFile.getOriginalFilename().contains(".")) {
                return false;
            }

            //保证扩展名不变
            int lastIndexOfFileName = imgFile.getOriginalFilename().lastIndexOf(".");
            String fileName =  lessonInfo.getLessonId() + imgFile.getOriginalFilename().substring(lastIndexOfFileName);
            Path paths = Paths.get(storeLocal+fileName);
            Files.write(paths, bytes, StandardOpenOption.CREATE);

            //加入lessonInfo
            lessonInfo.setImageName(fileName);
        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        //写入数据库
        return lessonInfoDao.insertLesson(lessonInfo) &
                lessonCollectDao.insertLessonCollct(lessonInfo.getLessonId()) &
                mkAuditFile &
                mkFile &
                mkRejectFile;
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
                String storeLocal = auditAddr + lessonId + "/";
                String originalName = files.get(fileName).getOriginalFilename();
                if(originalName == null) {
                    logger.info("--------文件名为空");
                    return false;
                }
                //保证扩展名不变
                int lastIndexOfFileName = originalName.lastIndexOf(".");
                Path paths = Paths.get(storeLocal +
                        fileName +
                        originalName.substring(lastIndexOfFileName));
                Files.write(paths, bytes, StandardOpenOption.CREATE);

            } catch (Exception e) {
                e.printStackTrace();
                logger.info("--------异常");
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
        File rejectFile = new File(rejectAddr + lessonId + "/" + videoName);
        if(pass) {
            return oldFile.renameTo(newFile);
        } else {
            return oldFile.renameTo(rejectFile);
        }
    }

    //获取待审核的视频列表
    public List<String> getAllUnauditLessonId() {

        List<String> lessonList = new LinkedList<>();
        //获取audit文件夹下所有不为空的文件夹列表
        File lessonDir = new File(auditAddr);
        File[] files = lessonDir.listFiles();
        for (File f:lessonDir.listFiles()) {
            //判断文件夹是否为空
            if(f.listFiles().length != 0){
                //不为空则添加
                lessonList.add(f.getName());
            }
        }
        return lessonList;
    }

    //todo: 获取课程的待审核列表
    public List<String> getAllUnauditVideo(String lessonId) {
        //课程待审核视频目录
        return null;
    }

    //获取课程视频列表
    //传入参数：课程id
    public List<String> getVideoList(String lessonId) {

        List<String> fileListName = new ArrayList<>();

        //获取课程根目录
        //Path path = Paths.get(baseAddr + lessonId);
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

    //获取课程对应教师id
    //传入参数：课程id
    public String getLessonTeacher(String lessonId) {
        return lessonInfoDao.selectLessonTeacher(lessonId);
    }

    //获取课程的全部信息
    public Lesson getLessonInfo(String lessonId){
        LessonInfo lessonInfo = lessonInfoDao.selectLessonInfo(lessonId);
        LessonCollect lessonCollect = lessonCollectDao.selectLessonCollectInfo(lessonId);
        System.out.println(lessonInfo + "\n" + lessonCollect);
        return new Lesson(lessonInfoDao.selectLessonInfo(lessonId),
                lessonCollectDao.selectLessonCollectInfo(lessonId));
    }

    //给课程点赞
    public boolean likeLesson(String lessonId) {
        return lessonCollectDao.updateLike(lessonId,
                lessonCollectDao.getLike(lessonId) + 1);
    }

    //给课程
    public boolean dislikeLesson(String lessonId) {
        return lessonCollectDao.updateDislike(lessonId,
                lessonCollectDao.getDislike(lessonId) + 1);
    }

    //获取收藏前十
    public List<LessonCollect> getTopCollected() {
        return lessonCollectDao.selectTopCollected();
    }

    //获取点赞前十
    public List<LessonCollect> getTopLiked() {
        return lessonCollectDao.selectTopLiked();
    }
}
