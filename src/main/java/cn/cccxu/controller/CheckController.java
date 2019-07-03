package cn.cccxu.controller;

import cn.cccxu.service.CheckService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Controller
public class CheckController {

    private CheckService checkService;

    CheckController (CheckService mCheckService){
        this.checkService = mCheckService;
    }

    @GetMapping(path="/user/check")
    @ResponseBody
    public boolean check(@RequestParam int gainedPoints, @RequestParam String userId) {
        return checkService.check(gainedPoints, userId);
    }
}
