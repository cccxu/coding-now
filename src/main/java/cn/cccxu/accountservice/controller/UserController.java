package cn.cccxu.accountservice.controller;

import cn.cccxu.accountservice.model.User;
import cn.cccxu.accountservice.service.LoginService;
import cn.cccxu.accountservice.service.RegisterService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class UserController {

    private RegisterService registerService;
    private LoginService loginService;

    @Autowired
    UserController(RegisterService mRegisterService, LoginService mLoginService) {
        this.registerService = mRegisterService;
        this.loginService = mLoginService;
    }

    //注册，通过json传入User对象
    @PostMapping(path = "/register")
    @ResponseBody
    public boolean register(@RequestBody User user){
        return registerService.register(user);
    }

    @GetMapping(path = "/getSalt")
    @ResponseBody
    public String getSalt(@RequestParam String userId){
        return loginService.getSalt(userId);
    }

    @PostMapping(path = "/signin")
    @ResponseBody
    public boolean signIn(@RequestBody JSONObject jsonObject){
        return loginService.checkUserLogin(jsonObject.get("userId").toString(),
                jsonObject.get("passwordHash").toString());
    }
}
