package cn.cccxu.dao;

import cn.cccxu.entity.PersonalCollection;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/4
 */

@Mapper
@Component
public interface PersonalCollectionDao {

    //添加收藏
    //传入参数：PersonalCollection
    @Insert("INSERT INTO tb_personal_collection " +
            "(user_id, lesson_id, collect_date) " +
            "VALUES (#{userId}, #{lessonId}, #{collectDate})")
    boolean insertCollect(PersonalCollection personalCollection);

    //获取用户收藏
    //传入参数：userId
    @Select("SELECT * " +
            "FROM tb_personal_collection " +
            "WHERE user_id = #{userId}")
    List<PersonalCollection> selectPersonalCollection(String userId);
}
