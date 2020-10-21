package com.ntvu.web2.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Tools {

    /**
     * 判断：给定的身份证号是否合法，不合法，抛出异常
     * @param idCard
     * @throws Exception
     */
    public static void validateIDCard(String idCard) throws Exception
    {
        if(idCard.length() != 18)
        {
            throw new Exception("身份证号长必须为18位");
        }
        for(int index = 0; index < idCard.length() ; index ++)
        {
            if(idCard.charAt(index) >= '0' && idCard.charAt(index) <= '9')
            {
                continue;
            }else{
                if(index == 17 && (idCard.charAt(index) == 'x' || idCard.charAt(index) == 'X' ) )
                {
                    continue;
                }else {
                    throw new Exception("身份证号长必须为18位,且是数字，最后一位可为x");
                }
            }
        }
    }


    /**
     * 利用md5算法，对给定的字符串进行加密处理
     * @param content 明文字符串
     * @return
     */
    public static String md5(String content)
    {
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

//    /**
//     * 判断给定的对象是否为空，如果为空，则显示缺省值
//     * @param obj 给定的对象
//     * @param defaultValue 缺省值
//     * @return Object
//     */
//    public static Object isEmpty(Object obj, String defaultValue) {
//        return obj == null ? defaultValue : obj.toString();
//    }

    /**
     * 判断给定的字符串是否为空，如果为空，则显示缺省值
     * @param str 给定的字符串
     * @param defaultValue 缺省值
     * @return String
     */
    public static String isEmpty(String str, String defaultValue) {
        return str == null || "".equals(str.trim()) ? defaultValue : str;
    }

    public static void main(String[] args) {
        System.out.println(md5("123"));
    }
}
