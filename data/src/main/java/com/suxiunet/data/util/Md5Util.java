package com.suxiunet.data.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 月光和我 on 2017/3/28.
 */
public class Md5Util {
    private Md5Util(){}
    
    private static Md5Util mInstance;

    public static Md5Util getInstance() {
        synchronized (Md5Util.class) {
            if (null == mInstance) {
                mInstance = new Md5Util();
            }
        }
        return mInstance;
    }

    /**
     * 获取字符串的md5值
     * @param value
     * @return
     */
    public String getMd5(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] result = digest.digest(value.getBytes());

            StringBuffer sb = new StringBuffer();
            for (byte b : result) {
                int i = b & 0xff;// 将字节转为整数
                String hexString = Integer.toHexString(i);// 将整数转为16进制

                if (hexString.length() == 1) {
                    hexString = "0" + hexString;// 如果长度等于1, 加0补位
                }

                sb.append(hexString);
            }

            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            // 如果算法不存在的话,就会进入该方法中
            e.printStackTrace();
            return "";
        }
    }
}
