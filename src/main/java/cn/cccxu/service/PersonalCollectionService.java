package cn.cccxu.service;

import cn.cccxu.dao.LessonCollectDao;
import cn.cccxu.dao.PersonalCollectionDao;
import cn.cccxu.entity.LessonCollect;
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
    private LessonCollectDao lessonCollectDao;

    @Autowired
    PersonalCollectionService(PersonalCollectionDao personalCollectionDao, LessonCollectDao lessonCollectDao) {
        this.personalCollectionDao = personalCollectionDao;
        this.lessonCollectDao = lessonCollectDao;
    }

    public boolean newPersonalCollect(PersonalCollection personalCollection) {
        LessonCollect lesson = new LessonCollect();
        lesson.setLessonId(personalCollection.getLessonId());
        lesson.setCollectedTimes(lessonCollectDao.selectLessonCollectTimes(personalCollection.getLessonId())+1);

        return personalCollectionDao.insertCollect(personalCollection) & lessonCollectDao.updateLessonCollect(lesson);
    }

    public List<PersonalCollection> getUserCollection(String userId) {
        return personalCollectionDao.selectPersonalCollection(userId);
    }
}
