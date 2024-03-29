package cn.cccxu.controller;

import cn.cccxu.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author 徐浩
 * created at 2019/07/01
 *
 * 普通用户、教师、管理员均使用本控制器进行登录
 * session会存储:
 *  1. 用户id， 普通用户与教师为 userId, 管理员为 adminId
 *  2. 用户类型，普通用户：user; 教师：teacher; 管理员：admin
 */

@RestController
public class LoginController {

    private LoginService loginService;

    @Autowired
    LoginController(LoginService mLoginService) {
        this.loginService = mLoginService;
    }



    @GetMapping(path = "/account/getSalt")
    public String getSalt(@RequestParam String userId){
        return loginService.getSalt(userId);
    }

    @PostMapping(path = "/account/login")
    public boolean login(@RequestBody JSONObject jsonObject,
                         HttpServletRequest request){
        //验证登录信息
        if(loginService.checkUserLogin(jsonObject.getString("userId"),
                jsonObject.getString("passwordHash"))){


            HttpSession httpSession = request.getSession();


            if(httpSession.getAttribute("userId") == null) {
                httpSession.setAttribute("userId", jsonObject.getString("userId"));
                httpSession.setAttribute("userType", "user");
            }

            return true;
        } else {
            return false;
        }
    }

    @PostMapping(path = "/account/teacherLogin")
    public boolean teacherLogin(@RequestBody JSONObject jsonObject,
                                HttpServletRequest request){
        if(loginService.checkTeacherLogin(jsonObject.getString("userId"),
                jsonObject.getString("passwordHash"))){

            HttpSession httpSession = request.getSession();
            if(httpSession.getAttribute("userId") == null) {
                httpSession.setAttribute("userId", jsonObject.getString("userId"));
                httpSession.setAttribute("userType", "teacher");
            }
            return true;
        } else {
            return false;
        }
    }

    @GetMapping(path = "/admin/getSalt")
    public String adminGetSalt(@RequestParam String adminId) {
        return loginService.getAdminSalt(adminId);
    }

    @PostMapping(path = "/admin/login")
    public boolean adminLogin(@RequestBody JSONObject jsonObject,
                              HttpServletRequest request) {
        if(loginService.checkAdminLogin(jsonObject.getString("adminId"),
                jsonObject.getString("passwordHash"))) {

            HttpSession httpSession = request.getSession();
            if(httpSession.getAttribute("userId") == null) {
                httpSession.setAttribute("userId", jsonObject.getString("adminId"));
                httpSession.setAttribute("userType", "admin");
            }
            return true;
        } else {
            return false;
        }
    }

    @PostMapping(path = "/account/refresh")
        public boolean getNewSession(@RequestParam String userId,
                HttpServletRequest request) {

        HttpSession httpSession = request.getSession();

        if (httpSession.getAttribute("userId") == null) {
            httpSession.setAttribute("userId", userId);
            httpSession.setAttribute("userType", "user");
            return true;
        } else {
            return false;
        }
    }
}
