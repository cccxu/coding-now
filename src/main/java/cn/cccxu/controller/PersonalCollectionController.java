package cn.cccxu.controller;

import cn.cccxu.entity.PersonalCollection;
import cn.cccxu.service.PersonalCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@Controller
public class PersonalCollectionController {

    private PersonalCollectionService personalCollectionService;

    @Autowired
    PersonalCollectionController(PersonalCollectionService personalCollectionService) {
        this.personalCollectionService = personalCollectionService;
    }

    @PostMapping(path = "/collection/newCollect")
    @ResponseBody
    public boolean collect(PersonalCollection personalCollection) {
        return personalCollectionService.newPersonalCollect(personalCollection);
    }

    @GetMapping(path = "/collection/getCollection")
    @ResponseBody
    public List<PersonalCollection> getCollection(@RequestParam("userId") String userId){
       return  personalCollectionService.getUserCollection(userId);
    }
}
