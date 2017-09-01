package com.spring.Util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密
 * 
 * @author zixie1991 date:2013-06-11 14:40:00
 */
public class MD5Util {

	private static String sault = "ag19k0fd3:2iNknJ";//默认密码加盐所用的字符串

	public MD5Util(String open){
		sault = open;
	}

	/**
	 * 32 位 MD5 小写
	 * 
	 * @param str
	 * @return
	 */
	public final static String calc(String str, String salt) {
		String step1 = calculateMD5(str, sault);
		return calculateMD5(step1, salt);
	}

	private static String calculateMD5(String source, String salt) {
		MessageDigest messageDigest = null;
		source = salt + source + sault;//加盐
		StringBuffer md5StrBuff = new StringBuffer();
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(source.getBytes("UTF-8"));
			byte[] byteArray = messageDigest.digest();
			for (int i = 0; i < byteArray.length; i++) {
				if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
					md5StrBuff.append("0").append(
							Integer.toHexString(0xFF & byteArray[i]));
				} else {
					md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
				}
			}

		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return md5StrBuff.toString();
	}

}
