package cn.cccxu.controller;

import cn.cccxu.entity.UserInfo;
import cn.cccxu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    /**
     *
     * @param file 头像文件，必须为图片，由前端进行检查
     * @param userId 不再使用，可为空
     * @return 新头像的地址
     * 修改：
     *  1. 不再使用传入的userId, 使用session中存储的id
     */
    @PostMapping(path="/user/setHeadImg")
    @ResponseBody
    public String setHeadImg(@RequestParam("file") MultipartFile file,
                             @RequestParam("userId") String userId,
                             HttpServletRequest request) {

        //检查用户是否登录
        if(request.getSession().getAttribute("userId") == null){
            return "Login first";
        } else {
        return userInfoService.setUserHeadImg(file,
                request.getSession().getAttribute("userId").toString());
        }
    }

    /**
     * @param userInfo 每次传入完整的用户信息类，但是用户头像字段会被忽略不会被修改
     * @return 修改结果
     * 注：为了最小化修改，使用userInfo中的userId, 添加和session中的userId的对比来保证用户身份
     */
    @PostMapping(path = "/user/setUserInfo")
    @ResponseBody
    public boolean setUserInfo(@RequestBody UserInfo userInfo,
                               HttpServletRequest request) {
        if(!request.getSession().getAttribute("userId").equals(userInfo.getUserId())){
            return false;
        } else {
            return userInfoService.setUserInfo(userInfo);
        }
    }
}
