package com.cfpt.base.utils;

import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2017-12-15.
 */
public class StringUtils {

    private static Random strGen = new Random();
    ;
    private static Random numGen = new Random();
    ;
    private static char[] numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
    ;
    private static char[] numbers = ("0123456789").toCharArray();
    ;

    /**
     * 产生随机字符串 *
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[strGen.nextInt(61)];
        }
        return new String(randBuffer);
    }

    /**
     * 产生随机数值字符串 *
     */
    public static final String randomNumStr(int length) {
        if (length < 1) {
            return null;
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbers[numGen.nextInt(9)];
        }
        return new String(randBuffer);
    }


    private static String getMD5(String source) {
        byte[] sourceByte = source.getBytes();
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f' };
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(sourceByte);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节

            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符

            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

                // 转换成 16 进制字符的转换

                byte byte0 = tmp[i]; // 取第 i 个字节

                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移

                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s.toUpperCase();
    }


    public static String getMD5Pwd(String pwd, Date date) {
        return getMD5(pwd + new MySimpleDateFormat("ssHHmmMMyyyydd").format(date));
    }

    public static String getMD5Pwd(String pwd) {
        return getMD5(pwd);
    }

}
