package cn.cccxu.service;

import cn.cccxu.dao.FollowersDao;
import cn.cccxu.entity.Followers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/8
 */

@Service
public class FollowersService {

    private FollowersDao followersDao;

    @Autowired
    FollowersService(FollowersDao followersDao) {
        this.followersDao = followersDao;
    }

    //关注用户
    //followerId来自于session
    public boolean addFollow(Followers followers) {
        return followersDao.insertFollow(followers);
    }

    //取消关注
    public boolean delFollow(Followers followers) {
        return followersDao.deleteFollow(followers);
    }

    //获取用户关注列表
    public List<String> getFollowedList(String followerId) {
        return followersDao.selectFollowed(followerId);
    }
}
