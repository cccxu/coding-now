package cn.cccxu.controller;

import cn.cccxu.entity.Followers;
import cn.cccxu.service.FollowersService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

@RestController
public class FollowersController {

    private FollowersService followersService;

    FollowersController(FollowersService followersService) {
        this.followersService = followersService;
    }

    @PostMapping(path = "/follow/add")
    public boolean addFollow(@RequestBody Followers followers,
                             HttpServletRequest request) {
        String followerId = request.getSession().getAttribute("userId").toString();

        if(followerId != null) {
            followers.setFollowerId(followerId);
            return followersService.addFollow(followers);
        } else {
            return false;
        }
    }

    @PostMapping(path = "/follow/del")
    public boolean cancelFollow(@RequestBody Followers followers,
                                HttpServletRequest request) {
        String followerId = request.getSession().getAttribute("userId").toString();

        if(followerId != null) {
            followers.setFollowerId(followerId);
            return followersService.delFollow(followers);
        } else {
            return false;
        }
    }

    @GetMapping(path = "/follow/getAll")
    public List<String> getFollowedId(HttpServletRequest request) {

        String followerId = request.getSession().getAttribute("userId").toString();

        if(followerId != null) {
            return followersService.getFollowedList(followerId);
        } else {
            return null;
        }
    }
}
