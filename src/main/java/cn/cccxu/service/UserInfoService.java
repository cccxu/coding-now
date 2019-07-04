package cn.cccxu.service;

import cn.cccxu.dao.UserInfoDao;
import cn.cccxu.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Service
public class UserInfoService {

    private UserInfoDao userInfoDao;

    @Autowired
    UserInfoService(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    public UserInfo getUserInfo(String userId) {
        return userInfoDao.selectUserInfo(userId);
    }

    //上传头像图片
    //规范化命名
    //生成对应的静态url
    //写入数据库并返回url
    public String setUserHeadImg(MultipartFile file, String userId) {

        if(file.isEmpty()){
            return "File is empty";
        }

        try {
            byte [] bytes = file.getBytes();
            String storeLocal = "/coding-now/resource/pictures/head-image/";
            //不同的扩展名要处理
            int lastIndexOfFileName = file.getOriginalFilename().lastIndexOf(".");
            Path paths = Paths.get(storeLocal
                    + userId
                    + file.getOriginalFilename().substring(lastIndexOfFileName));
            Files.write(paths, bytes, StandardOpenOption.CREATE_NEW);
            //静态资源方位url
            String url = "/pictures/head-image/" + userId + file.getOriginalFilename().substring(lastIndexOfFileName);
            //写入数据库
            if(!userInfoDao.updateUserHeadImage(url, userId)){
                return "ERROR";
            };
            //返回给客户端
            return url;
        } catch (Exception e){
            e.printStackTrace();
            return "ERROR";
        }

    }
}
