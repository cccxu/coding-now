package cn.cccxu.accountservice.controller;

import cn.cccxu.accountservice.model.User;
import cn.cccxu.accountservice.service.LoginService;
import cn.cccxu.accountservice.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class RegisterController {

    private RegisterService registerService;

    @Autowired
    RegisterController(RegisterService mRegisterService) {
        this.registerService = mRegisterService;
    }

    //注册，通过json传入User对象
    @PostMapping(path = "/register")
    @ResponseBody
    public boolean register(@RequestBody User user){
        return registerService.register(user);
    }
}
