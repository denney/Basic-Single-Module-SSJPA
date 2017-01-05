package com.youmeek.ssm;/**
 * Created by pc on 2017/1/3.
 */

import com.youmeek.ssm.dst.until.Constant;
import com.youmeek.ssm.dst.until.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试获取融云的token
 *
 * @author
 * @create 2017-01-03 9:52
 **/

public class Test_getToken {

    public static void main(String[] args) {


        Map params = new HashMap();
        params.put("userId", "jlk46j5");
        params.put("name", "Iroman");
        params.put("portraitUri", "http://photocdn.sohu.com/20170103/Img477592778.jpeg");


        String str = HttpUtil.post(Constant.Rong_IP+"/user/getToken.json", params, 3000, 3000, "UTF-8");
        System.out.println(str + "=================");
    }

}
