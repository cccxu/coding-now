package cn.cccxu.accountservice.controller;

import cn.cccxu.accountservice.service.LoginService;
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
