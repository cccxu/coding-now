package cn.cccxu.controller;

import cn.cccxu.service.LoginService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class LoginController {

    private LoginService loginService;

    @Autowired
    LoginController(LoginService mLoginService) {
        this.loginService = mLoginService;
    }



    @GetMapping(path = "/account/getSalt")
    @ResponseBody
    public String getSalt(@RequestParam String userId){
        return loginService.getSalt(userId);
    }

    @PostMapping(path = "/account/login")
    @ResponseBody
    public boolean login(@RequestBody JSONObject jsonObject){
        return loginService.checkUserLogin(jsonObject.get("userId").toString(),
                jsonObject.get("passwordHash").toString());
    }

    @PostMapping(path = "/account/teacherLogin")
    @ResponseBody
    public boolean teacherLogin(@RequestBody JSONObject jsonObject){
        return loginService.checkTeacherLogin(jsonObject.get("userId").toString(),
                jsonObject.get("passwordHash").toString());
    }

}
