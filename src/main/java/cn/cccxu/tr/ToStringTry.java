package cn.cccxu.tr;

import com.alibaba.fastjson.JSONObject;

/**
 * @author 徐浩
 * @version 1.0 at 2019/7/5
 */

public class ToStringTry {
    public static void main(String[] args) {
        Object o = "test 测试 ";
        System.out.println("字符串 Object 调用 toString()  --  " + o.toString().equals("test 测试 "));
        System.out.println(o.equals("test 测试 "));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("E", "test");
        jsonObject.put("C", "测试");
        System.out.println("json 测试  --  " + jsonObject.get("E").toString().equals("test") + "  " + jsonObject.get("C"));
    }
}
