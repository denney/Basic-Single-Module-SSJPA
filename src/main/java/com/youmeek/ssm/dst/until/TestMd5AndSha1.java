package com.youmeek.ssm.dst.until;/**
 * Created by pc on 2016/12/30.
 */

import com.alibaba.fastjson.support.odps.CodecCheck;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5和Sha1工具类
 *
 * @author
 * @create 2016-12-30 16:41
 **/

public class TestMd5AndSha1 {


    public static String md5(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

    public static String sha1(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for (int i = 0; i < bits.length; i++) {
            int a = bits[i];
            if (a < 0) a += 256;
            if (a < 16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }
    public static String  getsha1(String s) throws NoSuchAlgorithmException {
        String App_Secret = "VODDTjU0UT";
        String Nonce = RadomNumber.getRadom(9);
        String Timestamp = System.currentTimeMillis() + "";


        String data = App_Secret+Nonce+Timestamp;


        return sha1(data);

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        //            POST /user/getToken.json HTTP/1.1
//            Host: api.cn.ronghub.com
//            App-Key: tdrvipkstmr35
//            Nonce: 180439271
//            Timestamp: 1483085811
//            Signature: 6d669f5309631a7121af22d94abdb78587d266bf
//            Content-Type: application/x-www-form-urlencoded
//            Content-Length: 39
//
//            userId=12131&name=12121&portraitUri=121


        String App_Secret = "VODDTjU0UT";
        String Nonce = RadomNumber.getRadom(9);
        String Timestamp = System.currentTimeMillis() + "";


        String data = App_Secret+Nonce+Timestamp;

        System.out.println("SHA1 : " + sha1(data));
    }

}
