package com.youmeek.ssm.dst.until;/**
 * Created by pc on 2016/12/30.
 */

/**
 * 获得指定位数的随机数
 *
 * @author
 * @create 2016-12-30 16:51
 **/

public  class RadomNumber {


    /**
     * 需要的长度
     *
     * @param length
     */
    public static String getRadom(int length) {
        //获取一个随机数
        double rand = Math.random();
        //将随机数转换为字符串
        String str = String.valueOf(rand).replace("0.", "");
        return str.substring(0, length);
    }
}
