package cn.cccxu.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Comparator;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/9
 */

public class LessonCollectedComparator implements Comparator<JSONObject> {

    @Override
    public int compare(JSONObject j1, JSONObject j2){
        if(j1.getIntValue("collectedTimes")>j2.getIntValue("collectedTimes")){	//greater
            return -1;
        }else if(j1.getIntValue("collectedTimes")==j2.getIntValue("collectedTimes")){	//equal
            return 0;
        }else{	//less
            return 1;
        }
    }
}
