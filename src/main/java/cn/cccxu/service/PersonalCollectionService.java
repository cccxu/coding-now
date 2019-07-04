package cn.cccxu.service;

import cn.cccxu.dao.PersonalCollectionDao;
import cn.cccxu.entity.PersonalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@Service
public class PersonalCollectionService {

    private PersonalCollectionDao personalCollectionDao;

    @Autowired
    PersonalCollectionService(PersonalCollectionDao personalCollectionDao) {
        this.personalCollectionDao = personalCollectionDao;
    }

    public boolean newPersonalCollect(PersonalCollection personalCollection) {
        return personalCollectionDao.insertCollect(personalCollection);
    }

    public List<PersonalCollection> getUserCollection(String userId) {
        return personalCollectionDao.selectPersonalCollection(userId);
    }
}
