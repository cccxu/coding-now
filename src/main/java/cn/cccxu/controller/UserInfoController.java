package cn.cccxu.controller;

import cn.cccxu.entity.UserInfo;
import cn.cccxu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Controller
public class UserInfoController {

    private UserInfoService userInfoService;

    @Autowired
    UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping(path="/info/getUserInfo")
    @ResponseBody
    public UserInfo getUserInfo(@RequestParam String userId) {
        return userInfoService.getUserInfo(userId);
    }

    @PostMapping(path="/user/setHeadImg")
    @ResponseBody
    public String setHeadImg(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        return userInfoService.setUserHeadImg(file, userId);
    }
}
