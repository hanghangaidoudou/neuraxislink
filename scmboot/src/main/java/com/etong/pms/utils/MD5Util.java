package com.etong.pms.utils;

import java.security.MessageDigest;

/**
 * MD5Util 加密工具类
 */
public class  MD5Util {

  /**
   * 生成加密字符串
   */
  public static String getMD5(String message) {
    String md5 = "";
    try {
      // 创建一个md5算法对象
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] messageByte = message.getBytes("UTF-8");
      // 获得MD5字节数组,16*8=128位
      byte[] md5Byte = md.digest(messageByte);
      // 转换为16进制字符串
      md5 = bytesToHex(md5Byte);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return md5;
  }

  /**
   * 二进制转十六进制
   */
  public static String bytesToHex(byte[] bytes) {
    StringBuffer hexStr = new StringBuffer();
    int num;
    for (int i = 0; i < bytes.length; i++) {
      num = bytes[i];
      if (num < 0) {
        num += 256;
      }
      if (num < 16) {
        hexStr.append("0");
      }
      hexStr.append(Integer.toHexString(num));
    }
    return hexStr.toString().toUpperCase();
  }
}
