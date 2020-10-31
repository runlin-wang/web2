package com.ntvu.web2.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {

    /**
     * 判断：给定的身份证号是否合法，不合法，抛出异常
     * @param idCard 身份证号码
     * @throws Exception 身份证长度异常或者数据异常
     */
    public static void validateIDCard(String idCard) throws Exception {
        if(idCard.length() != 18)
        {
            throw new Exception("身份证号长必须为18位");
        }
        for(int index = 0; index < idCard.length() ; index ++)
        {
            if (idCard.charAt(index) < '0' || idCard.charAt(index) > '9') {
                if (index != 17 || (idCard.charAt(index) != 'x' && idCard.charAt(index) != 'X')) {
                    throw new Exception("身份证号长必须为18位,且是数字，最后一位可为x");
                }
            }
        }
    }

    /**
     * 利用md5算法，对给定的字符串进行加密处理
     * @param content 明文字符串
     * @return String 加密后的字符串
     */
    public static String md5(String content) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            // 计算md5函数
            md.update(content.getBytes());
            // digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
            // BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
            return new BigInteger(1, md.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 判断对象是否为 null 或者 toString 方法为空
     * @param obj 传入的对象
     * @return obj == null || "".equals(obj.toString().trim())
     */
    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || "".equals(obj.toString().trim());
    }

    /**
     * 判断给定的对象是否为空，如果为空，则显示缺省值
     * @param obj 给定的对象
     * @param defaultValue 缺省值
     * @return obj 为空时返回 defaultValue，否则返回obj.toString()
     */
    public static Object getNullOrEmpty(Object obj, Object defaultValue) {
        return isNullOrEmpty(obj) ? defaultValue : obj;
    }

    /**
     * 判断字符串是否为 null 或者 为空
     * @param str 传入的字符串
     * @return str == null || "".equals(str.trim())
     */
    public static boolean isNullOrEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    /**
     * 判断字符串是否为 null 或者 为空
     * @param str 传入的字符串
     * @param defaultValue 默认值
     * @return 如果字符串为 null 或为空，则返回默认值
     */
    public static String getNullOrEmpty(String str, String defaultValue) {
        return isNullOrEmpty(str) ? defaultValue : str;
    }

    /**
     * 判断给定的数字是否大于 0，大于 0 返回 num，否则返回缺省值
     * @param num 数字
     * @param defaultValue 缺省值
     * @return int
     */
    public static int getGreaterThanZero(int num, int defaultValue) {
        return num > 0 ? num : defaultValue;
    }

    /**
     * 判断给定的数字字符串是否为空且是否大于 0
     * @param num 字符串数字
     * @param defaultValue 缺省值
     * @return int
     */
    public static int getGreaterThanZero(String num, int defaultValue) {
        return isNumber(num) ? getGreaterThanZero(Integer.parseInt(num), defaultValue) : defaultValue;
    }

    /**
     * 判断字符串是否为纯数字
     * @param str 字符串
     * @return 字符串非空且为纯数字时返回 true
     */
    public static boolean isNumber(String str) {
        return !isNullOrEmpty(str) && str.matches("^[0-9]*$");
    }

    public static void main(String[] args) {
        System.out.println(md5("123"));
    }
}
