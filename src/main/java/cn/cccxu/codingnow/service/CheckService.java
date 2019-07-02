package cn.cccxu.codingnow.service;

import cn.cccxu.codingnow.dao.CheckDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 徐浩
 * created at 2019/07/01
 */

@Service
public class CheckService {

    private CheckDao checkDao;

    @Autowired
    CheckService (CheckDao mCheckDao) {
        this.checkDao = mCheckDao;
    }

    public boolean check(int gainedPoints, String userId) {
        return checkDao.setMemberPoints(checkDao.getMemberPoints(userId)+gainedPoints, userId);
    }
}
