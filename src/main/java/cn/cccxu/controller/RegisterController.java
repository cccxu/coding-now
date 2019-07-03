package cn.cccxu.controller;

import cn.cccxu.model.User;
import cn.cccxu.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(path = "/account/register")
    @ResponseBody
    public boolean register(@RequestBody User user){
        return registerService.register(user);
    }

    @GetMapping(path="/account/checkUserIdUsable")
    @ResponseBody
    public boolean checkUserIdUsable(@RequestParam String userId) {
        return registerService.checkUserIdUseable(userId);
    }
}
