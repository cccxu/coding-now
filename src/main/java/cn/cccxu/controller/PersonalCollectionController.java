package cn.cccxu.controller;

import cn.cccxu.entity.PersonalCollection;
import cn.cccxu.service.PersonalCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@RestController
public class PersonalCollectionController {

    private PersonalCollectionService personalCollectionService;

    @Autowired
    PersonalCollectionController(PersonalCollectionService personalCollectionService) {
        this.personalCollectionService = personalCollectionService;
    }

    @PostMapping(path = "/collection/newCollect")
    public boolean collect(@RequestBody PersonalCollection personalCollection,
                           HttpServletRequest request) {
        String userId = request.getSession().getAttribute("userId").toString();
        if(userId == null){
            return false;
        } else {
            personalCollection.setUserId(userId);
            return personalCollectionService.newPersonalCollect(personalCollection);
        }
    }

    @PostMapping(path = "/collection/getCollection")
    public List<PersonalCollection> getCollection(HttpServletRequest request){
       return  personalCollectionService.getUserCollection(request.getSession().getAttribute("userId").toString());
    }
}
