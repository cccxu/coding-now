package cn.cccxu.service;

import cn.cccxu.dao.UserInfoDao;
import cn.cccxu.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

            if(file.getOriginalFilename() == null || !file.getOriginalFilename().contains(".")){
                return "Illegal File Name";
            }

            //保证扩展名不变
            int lastIndexOfFileName = file.getOriginalFilename().lastIndexOf(".");
            Path paths = Paths.get(storeLocal
                    + userId
                    + file.getOriginalFilename().substring(lastIndexOfFileName));

            Files.write(paths, bytes, StandardOpenOption.CREATE);

            //静态资源访问url
            String url = "/pictures/head-image/" + userId + file.getOriginalFilename().substring(lastIndexOfFileName);
            //写入数据库
            if(!userInfoDao.updateUserHeadImage(url, userId)){
                return "ERROR";
            }
            //返回给客户端
            return url;
        } catch (IOException e){
            e.printStackTrace();
            return "ERROR";
        }

    }

    //修改用户信息
    //传入完整userInfo
    public boolean setUserInfo(UserInfo userInfo) {
        return userInfoDao.updateUserInfo(userInfo);
    }

    //获取用户昵称
    public Map<String, String> getUserNickName(List<String> userId) {
        Map<String, String> nm = new HashMap<>();

        for(String user : userId){
            nm.put(user, userInfoDao.selectUserNickName(user));
        }
        return nm;
    }

    //获取用户头像
    public Map<String, String> getUserHeadImg(List<String> userId) {
        Map<String, String> nm = new HashMap<>();

        for(String user : userId){
            nm.put(user, userInfoDao.selectUserHeadImg(user));
        }
        return nm;
    }
}
