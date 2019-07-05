package cn.cccxu.controller;

import cn.cccxu.service.CheckService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @PostMapping(path="/user/check")
    @ResponseBody
    public boolean check(@RequestBody JSONObject jsonObject,
                         HttpServletRequest request) {

        HttpSession httpSession = request.getSession();
        if(httpSession.getAttribute("userId") != null){
            return checkService.check(Integer.parseInt(jsonObject.get("gainedPoints").toString()),
                    (String)httpSession.getAttribute("userId"));
        } else {
            return false;
        }
    }
}
