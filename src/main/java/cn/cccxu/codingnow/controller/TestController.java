package cn.cccxu.codingnow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 徐浩
 * created at 2019/07/02
 */

@Controller
public class TestController {

    @GetMapping(path="/hello")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
}
